package com.hluck.movieinfo.main.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.movieinfo.main.domain.models.Media
import com.hluck.movieinfo.main.domain.repository.GenreRepository
import com.hluck.movieinfo.main.domain.repository.MediaRepository
import com.hluck.movieinfo.util.Constants
import com.hluck.movieinfo.util.Constants.ALL
import com.hluck.movieinfo.util.Constants.MOVIE
import com.hluck.movieinfo.util.Constants.NOW_PLAYING
import com.hluck.movieinfo.util.Constants.POPULAR
import com.hluck.movieinfo.util.Constants.TOP_RATED
import com.hluck.movieinfo.util.Constants.TRENDING_TIME
import com.hluck.movieinfo.util.Constants.TV
import com.hluck.movieinfo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) :ViewModel(){

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    val showSplashScreen = mutableStateOf(true)

    init {
        viewModelScope.launch {
            delay(500)
            showSplashScreen.value = false
        }
    }


    fun onEvent(event: MainUiEvents) {

    }
}