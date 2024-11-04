package com.hluck.myquotes.ui.screens.detailscreens

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.myquotes.common.logd
import com.hluck.myquotes.domain.models.Quote
import com.hluck.myquotes.domain.usecase.GetSingleQuoteUseCase
import com.hluck.myquotes.network.NetworkUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val singleQuoteUseCase: GetSingleQuoteUseCase
):ViewModel() {

    var singleState = MutableStateFlow<NetworkUIState<Quote>>(NetworkUIState.LOADING<Quote>())

    init {
        getSingleQuote(id = savedStateHandle.get<String>("id") ?: "")
    }

    private fun getSingleQuote(id:String){
        "id: $id".logd()
        singleState.tryEmit(NetworkUIState.LOADING())
        singleQuoteUseCase(id = id).onEach {
            singleState.tryEmit(NetworkUIState.SUCCESS(it))
        }.launchIn(viewModelScope)
    }

}