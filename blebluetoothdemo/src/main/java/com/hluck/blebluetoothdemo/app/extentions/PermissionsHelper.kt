package com.hluck.blebluetoothdemo.app.extentions

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hluck.blebluetoothdemo.R

/**
 * 当前上下文是否已获取到相关权限
 */
fun Context.hasPermission(permissionType: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permissionType
    ) == PackageManager.PERMISSION_GRANTED
}

/**
 * 检查当前上下文是否已获取到蓝牙权限
 */
fun Context.hasRequiredBluetoothPermissions(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {//Android12 以上需要同时获取到扫描和连接权限
        hasPermission(Manifest.permission.BLUETOOTH_SCAN) && hasPermission(Manifest.permission.BLUETOOTH_CONNECT)
    } else {
        hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

/**
 * 请求蓝牙相关权限
 */
fun Activity.requestRelevantBluetoothPermissions(requestCode: Int) {
    if (hasRequiredBluetoothPermissions()) {
        "已经获取到蓝牙权限，无需重复获取".logd()
        return
    }
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (bluetoothPermissionRationaleRequired()) {
                displayNearbyDevicesPermissionRationale(requestCode)
            } else {
                requestNearbyDevicesPermission(requestCode)
            }
        }

        Build.VERSION.SDK_INT < Build.VERSION_CODES.S -> {
            if (locationPermissionRationaleRequired()) {
                displayLocationPermissionRationale(requestCode)
            } else {
                requestLocationPermission(requestCode)
            }
        }
    }
}

//获取定位权限相关 begin
/**
 * 检查是否需要显示定位权限的提示
 */
private fun Activity.locationPermissionRationaleRequired(): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
}

/**
 * 请求定位权限的提示弹窗
 */
private fun Activity.displayLocationPermissionRationale(requestCode: Int) {
    runOnUiThread {
        AlertDialog.Builder(this)
            .setTitle(R.string.location_permission_required)
            .setMessage(R.string.location_permission_rationale)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestLocationPermission(requestCode)
            }.setNegativeButton(R.string.quit) { _, _ ->
                finishAndRemoveTask()
            }
            .setCancelable(false)
            .show()
    }
}

/**
 * 请求定位权限
 */
private fun Activity.requestLocationPermission(requestCode: Int) {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        requestCode
    )
}
//get定位权限相关 end


//获取附近蓝牙设备权限相关 begin
/**
 *是否要提示用户获取nearby蓝牙权限
 */
private fun Activity.bluetoothPermissionRationaleRequired(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        ActivityCompat.shouldShowRequestPermissionRationale(
            this, Manifest.permission.BLUETOOTH_SCAN
        ) || ActivityCompat.shouldShowRequestPermissionRationale(
            this, Manifest.permission.BLUETOOTH_CONNECT
        )
    } else {
        false
    }
}

/**
 * 显示nearby权限提示弹窗
 */
@RequiresApi(Build.VERSION_CODES.S)
private fun Activity.displayNearbyDevicesPermissionRationale(requestCode: Int) {
    runOnUiThread {
        AlertDialog.Builder(this)
            .setTitle(R.string.bluetooth_permission_required)
            .setMessage(R.string.bluetooth_permission_rationale)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestNearbyDevicesPermission(requestCode)
            }.setNegativeButton(R.string.quit) { _, _ ->
                finishAndRemoveTask()
            }
            .setCancelable(false)
            .show()
    }
}

/**
 * 请求nearby蓝牙设备权限
 */
@RequiresApi(Build.VERSION_CODES.S)
private fun Activity.requestNearbyDevicesPermission(requestCode: Int) {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT),
        requestCode
    )
}
//获取附近蓝牙设备权限相关 end

