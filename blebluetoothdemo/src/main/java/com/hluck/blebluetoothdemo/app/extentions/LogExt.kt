package com.hluck.blebluetoothdemo.app.extentions

import android.util.Log

private const val TAG = "BLEDemo"
fun String.logd(tag:String = TAG){
    Log.d(tag,this)
}

fun String.loge(tag:String = TAG){
    Log.e(tag,this)
}