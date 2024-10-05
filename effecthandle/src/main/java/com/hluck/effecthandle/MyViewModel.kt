package com.hluck.effecthandle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {

    private val _nameState = MutableStateFlow("")
    val nameState = _nameState.asStateFlow()

    fun loadName(){
        viewModelScope.launch {
            Log.d("TAG","loadName()")
            delay(1500)
            _nameState.update { "Ahmed" }
        }
    }

    fun addName(){
        _nameState.update { it + " new" }
    }
}