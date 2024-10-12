package com.hluck.firstcomposeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.firstcomposeapp.remote.Constants.MOVIE
import com.hluck.firstcomposeapp.remote.Constants.POPULAR
import com.hluck.firstcomposeapp.remote.MediaRepository
import com.hluck.firstcomposeapp.remote.Resource
import com.hluck.firstcomposeapp.remote.api.Companion.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val mediaRepository: MediaRepository
) :ViewModel(){

    fun loadPopularMovies(){
        viewModelScope.launch {

            mediaRepository
                .getMoviesAndTvSeriesList(
                    false,
                    false,
                    MOVIE,
                    POPULAR,
                    1,
                    API_KEY
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { mediaList ->
                                "成功了！".toast()
                            }
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {

                        }
                    }
                }
        }
    }
}