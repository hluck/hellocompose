package com.hluck.fowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

class TestViewModel : ViewModel() {

    var flow = flow {
        for (i in 1..39) {
//            "发射数据:$i".logd()
            emit(i)
            kotlinx.coroutines.delay(2000)
        }
//        throw Exception()
    }.stateIn(
        viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(5000)
    )
//        .shareIn(
//        viewModelScope, SharingStarted.WhileSubscribed(5000)
//    )//转换成热流，流存活5秒，旋转屏幕时数据不会重置

//        .onStart {
//        "开始发射数据".logd()
//    }.onCompletion { e ->
//        if (e != null) "有异常".logd() else "数据发射完成".logd()
//    }.catch {
//        "捕获到异常".logd()
//    }
}