package com.hluck.playmusicdemo.ui

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MediaViewModel:ViewModel() {
    private var currentDuration:CountDownTimer? = null
    private val _currentMinutes = MutableStateFlow<Int>(0)
    val currentMinutes = _currentMinutes.asStateFlow()
    private val isFinish = MutableStateFlow(false)
    val isFinishState = isFinish.asStateFlow()

    private val _isPaused = MutableStateFlow(true)
    val isPaused = _isPaused.asStateFlow()

    fun getMediaDuration(mediaPlayer: MediaPlayer){
        viewModelScope.launch {
            while (mediaPlayer.isPlaying){
                val current = mediaPlayer.currentPosition/1000
                _currentMinutes.value = current
//                delay(500)
                delay(500)
                "current:$current".logd()
                if (current == (mediaPlayer.duration/1000 - 1)){
                    stopTimer()
                }
            }
        }


//        currentDuration = object : CountDownTimer(mediaPlayer.duration.toLong(),500){
//            override fun onTick(milliSec: Long) {
//                _currentMinutes.value = mediaPlayer.currentPosition/1000
//            }
//
//            override fun onFinish() {
//                "onFinish".logd()
//                isFinish.value = true
//                stopTimer()
//            }
//        }

//        currentDuration?.start()
    }

    fun stopTimer(){
        "stopTimer".logd()
        currentDuration?.cancel()
        _isPaused.value = true
        isFinish.value = true
        currentDuration = null
        _currentMinutes.value = 0
    }


    fun pause(){
        _isPaused.value = true
    }

    fun start(){
        _isPaused.value = false
    }


}