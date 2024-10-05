package com.hluck.blebluetoothdemo.ui.viewmodel

import android.bluetooth.le.ScanResult
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class BluetoothViewModel:ViewModel() {
    private val _scanResults:MutableList<ScanResult> = mutableStateListOf()
    val scanResults:MutableList<ScanResult> = _scanResults

    fun addScanResult(result:ScanResult){
        _scanResults.add(result)
    }
}