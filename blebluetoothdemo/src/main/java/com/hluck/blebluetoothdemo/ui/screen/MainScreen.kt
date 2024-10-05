package com.hluck.blebluetoothdemo.ui.screen

import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hluck.blebluetoothdemo.ui.viewmodel.BluetoothViewModel

@Composable
fun MainScreen(
    viewModel: BluetoothViewModel,
    onScanClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Button (
                onClick = { onScanClick() }
            ) {
                Text("扫描附近设备")
            }
        }
        items(viewModel.scanResults) { scanResult ->
            ScanResultItem(scanResult,onItemClick)
        }
    }

}

@SuppressLint("MissingPermission")
@Composable
fun ScanResultItem(
    scanResult: ScanResult,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth().clickable { onItemClick() }
    ) {
        Text(
            text = scanResult.device.name ?: "Unknown"
        )
        Text(
            text = scanResult.device.address ?: "Unknown"
        )
        Text(
            text = "${scanResult.rssi} dBm"
        )
    }
}