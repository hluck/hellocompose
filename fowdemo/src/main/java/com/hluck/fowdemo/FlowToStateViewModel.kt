package com.hluck.fowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FlowToStateViewModel : ViewModel() {

    var flow = flow<Int> {
        for (i in 1..8) {
            emit(i)
            kotlinx.coroutines.delay(2000)
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),0
    )
}