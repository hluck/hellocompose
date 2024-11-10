package com.hluck.myquotes.ui.screens.homescreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.myquotes.domain.models.HomeScreenModel
import com.hluck.myquotes.domain.usecase.QuoteUseCase
import com.hluck.myquotes.domain.usecase.RandomQuoteUseCase
import com.hluck.myquotes.network.NetworkUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quoteUseCase: QuoteUseCase,
    private val randomQuoteUseCase: RandomQuoteUseCase
) : ViewModel(){

    var quoteFlow = MutableStateFlow<NetworkUIState<HomeScreenModel>>(NetworkUIState.LOADING())
        private set

    init {
        getAllQuotes()
    }

    private fun getAllQuotes() {
        quoteFlow.tryEmit(NetworkUIState.LOADING())
        quoteUseCase().combine(randomQuoteUseCase()){ allQuotes, randomQuote ->
            quoteFlow.tryEmit(
                NetworkUIState.SUCCESS(HomeScreenModel(randomQuote, allQuotes))
            )
        }.launchIn(viewModelScope)
    }
}