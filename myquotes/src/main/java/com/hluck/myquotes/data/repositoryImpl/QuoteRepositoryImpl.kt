package com.hluck.myquotes.data.repositoryImpl

import androidx.compose.ui.platform.LocalDensity
import com.hluck.myquotes.data.remote.Api
import com.hluck.myquotes.domain.models.AllQuotes
import com.hluck.myquotes.domain.models.Quote
import com.hluck.myquotes.domain.repository.QuoteRepository
import retrofit2.Response
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val apiInterface:Api
): QuoteRepository {
    override suspend fun getAllQuotes(): Response<AllQuotes> {
        return apiInterface.getAllQuotes()
    }

    override suspend fun getRandomQuote(): Response<Quote> {
        return apiInterface.getRandomQuote()
    }

    override suspend fun getSingleQuote(id: String): Response<Quote> {

        return apiInterface.getSingleQuote(id)
    }
}