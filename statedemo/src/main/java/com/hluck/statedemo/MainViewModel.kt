package com.hluck.statedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    val color = savedStateHandle.getStateFlow("color",0xffffffff)

    fun changeColor(){
        savedStateHandle["color"] = 0xff0000ff
    }

}