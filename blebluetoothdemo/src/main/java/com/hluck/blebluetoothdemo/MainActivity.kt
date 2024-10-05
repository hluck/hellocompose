package com.hluck.blebluetoothdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hluck.blebluetoothdemo.app.extentions.hasRequiredBluetoothPermissions
import com.hluck.blebluetoothdemo.app.extentions.logd
import com.hluck.blebluetoothdemo.app.extentions.loge
import com.hluck.blebluetoothdemo.app.extentions.requestRelevantBluetoothPermissions
import com.hluck.blebluetoothdemo.ui.screen.MainScreen
import com.hluck.blebluetoothdemo.ui.theme.HelloComposeTheme
import com.hluck.blebluetoothdemo.ui.viewmodel.BluetoothViewModel

private const val PERMISSION_REQUEST_CODE = 1

class MainActivity : ComponentActivity() {
    /*******************************************
     * Properties
     *******************************************/

    private val viewModel by lazy {
        ViewModelProvider(this)[BluetoothViewModel::class.java]
    }

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private var isScanning = mutableStateOf(false)

    private val bluetoothEnablingResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            "蓝牙已开启".logd()
        } else {
            "用户驳回或拒绝蓝牙提示".logd()
        }
    }

    /*******************************************
     * Activity function overrides
     *******************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val viewModel:BluetoothViewModel = viewModel()

            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MainScreen(
                            viewModel,
                            onScanClick = {
                                startBleScan()
                            },
                            onItemClick = {}
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!bluetoothAdapter.isEnabled) {
            promptToEnableBluetooth()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isScanning.value) {
            stopScan()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return
        }
        if (permissions.isEmpty() && grantResults.isEmpty()) {
            "onRequestPermissionsResult中的权限和grantResults数组为空".loge()
            "这可能是由于用户交互中断而取消的".loge()
            return
        }
        // Log permission request outcomes
        val resultsDescriptions = grantResults.map {
            when (it) {
                PackageManager.PERMISSION_DENIED -> "Denied"
                PackageManager.PERMISSION_GRANTED -> "Granted"
                else -> "Unknown"
            }
        }
        val containsPermanentDenial = permissions.zip(grantResults.toTypedArray()).any {
            it.second == PackageManager.PERMISSION_DENIED &&
                    !ActivityCompat.shouldShowRequestPermissionRationale(this, it.first)
        }
        val containsDenial = grantResults.any { it == PackageManager.PERMISSION_DENIED }
        val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        when {
            containsPermanentDenial -> {
                "用户永久拒绝授予权限".loge()
                "请求从应用程序设置手动授予权限".loge()
                promptManualPermissionGranting()
            }

            containsDenial -> {
                // 仍然可以重新请求权限
                requestRelevantBluetoothPermissions(PERMISSION_REQUEST_CODE)
            }

            allGranted && hasRequiredBluetoothPermissions() -> {
                startBleScan()
            }

            else -> {
                "处理权限时遇到意外情况".loge()
                recreate()
            }
        }
    }

    /*******************************************
     * Private functions
     *******************************************/
    private fun promptToEnableBluetooth() {
        if (hasRequiredBluetoothPermissions() && !bluetoothAdapter.isEnabled) {
            Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE).apply {
                bluetoothEnablingResult.launch(this)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startBleScan() {
        if (!hasRequiredBluetoothPermissions()) {
            requestRelevantBluetoothPermissions(PERMISSION_REQUEST_CODE)
        } else if (!bluetoothAdapter.isEnabled) {
            promptToEnableBluetooth()
        } else {
            viewModel.scanResults.clear()
            bleScanner.startScan(null, scanSettings, scanCallBack)
            isScanning.value = true
        }
    }

    @SuppressLint("MissingPermission")
    private fun stopScan() {
        if (hasRequiredBluetoothPermissions() && bluetoothAdapter.isEnabled) {
            bleScanner.stopScan(scanCallBack)
            isScanning.value = false
        }
    }

    /**
     * 提示用户手动授予权限
     */
    private fun promptManualPermissionGranting() {
        AlertDialog.Builder(this)
            .setTitle(R.string.please_grant_relevant_permissions)
            .setMessage(R.string.app_settings_rationale)
            .setPositiveButton(R.string.app_settings) { _, _ ->
                try {

                } catch (e: ActivityNotFoundException) {
                    if (!isFinishing) {
                        Toast.makeText(
                            this,
                            R.string.cannot_launch_app_settings,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                finish()
            }
            .setNegativeButton(R.string.quit) { _, _ ->
                finishAndRemoveTask()
            }
            .setCancelable(false)
            .show()
    }


    /*******************************************
     * Callback bodies
     *******************************************/

    @SuppressLint("MissingPermission")
    private val scanCallBack = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            val indexQuery =
                viewModel.scanResults.indexOfFirst { it.device.address == result?.device?.address }

            if (indexQuery != -1) { //已存在具有相同地址的扫描结果
                viewModel.scanResults[indexQuery] = result!!
            } else {
                "搜索到BLE蓝牙设备：${result?.device?.name ?: "未知名设备"},address:${result?.device?.address}"
                viewModel.addScanResult(result!!)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            "扫描失败！errorCode:$errorCode".loge()
        }
    }
}
