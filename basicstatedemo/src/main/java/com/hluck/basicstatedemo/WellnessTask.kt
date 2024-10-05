package com.hluck.basicstatedemo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id:Int,
    val label:String,
    initChecked:Boolean = false
){
    var checked:Boolean by mutableStateOf(initChecked)
}
