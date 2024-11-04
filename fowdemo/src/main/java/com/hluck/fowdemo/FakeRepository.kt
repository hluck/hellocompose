package com.hluck.fowdemo

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlin.math.log

fun String.logd(tag:String = "TAG"){
    Log.d(tag,this)
}

class FakeRepository {


    fun generatorNumberUsingFlow() = flow<Int> {
        for ( i in 1..10){
            emit(i)
            delay(2000)
        }
    }
}