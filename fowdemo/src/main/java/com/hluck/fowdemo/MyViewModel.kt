package com.hluck.fowdemo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository = FakeRepository()

    private val _number = mutableStateOf(0)
    val number: State<Int>
        get() = _number

    init {
        viewModelScope.launch {
            repository.generatorNumberUsingFlow()
                .map {
                    it * it
                }
                .onEach {
//                    _number.value = it
//                    "num:$it".logd()
                }.launchIn(this)

            repository.generatorNumberUsingFlow()
                .onEach {
//                    _number.value = it
//                    "num:$it".logd()
                }.launchIn(this)
        }
//        "收集完成".logd()
    }
}