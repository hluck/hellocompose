package com.hluck.myquotes.domain.repository

import com.hluck.myquotes.domain.models.AllQuotes
import com.hluck.myquotes.domain.models.Quote
import retrofit2.Response

interface QuoteRepository {

    suspend fun getAllQuotes(): Response<AllQuotes>

    suspend fun getRandomQuote(): Response<Quote>

    suspend fun getSingleQuote(id:String): Response<Quote>
}