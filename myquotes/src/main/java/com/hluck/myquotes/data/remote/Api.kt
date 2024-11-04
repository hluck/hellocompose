package com.hluck.myquotes.data.remote

import com.hluck.myquotes.domain.models.AllQuotes
import com.hluck.myquotes.domain.models.Quote
import com.hluck.myquotes.network.ALL_QUOTES
import com.hluck.myquotes.network.GET_SINGLE_QUOTE
import com.hluck.myquotes.network.RANDOM_QUOTE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET(ALL_QUOTES)
    suspend fun getAllQuotes():Response<AllQuotes>

    @GET(RANDOM_QUOTE)
    suspend fun getRandomQuote():Response<Quote>

    @GET(GET_SINGLE_QUOTE)
    suspend fun getSingleQuote(
        @Path("id") id:String
    ):Response<Quote>
}