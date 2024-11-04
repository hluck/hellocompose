package com.hluck.myquotes.domain.usecase

import com.hluck.myquotes.domain.models.Quote
import com.hluck.myquotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSingleQuoteUseCase @Inject constructor(
    private val quoteRepository:QuoteRepository
) {

    operator fun invoke(id:String) = flow<Quote> {
        quoteRepository.getSingleQuote(id).let {
            if (it.isSuccessful){
                emit(it.body()!!)
            }
        }
    }
}