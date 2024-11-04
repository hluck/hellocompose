package com.hluck.myquotes.domain.usecase

import com.hluck.myquotes.domain.models.AllQuotes
import com.hluck.myquotes.domain.models.Quote
import com.hluck.myquotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    operator fun invoke():Flow<List<Quote>> = flow {
        quoteRepository.getAllQuotes()?.let {
            if (it.isSuccessful){
                it.body()?.quotes?.let { it1 -> emit(it1) }
            }
        }
    }
}