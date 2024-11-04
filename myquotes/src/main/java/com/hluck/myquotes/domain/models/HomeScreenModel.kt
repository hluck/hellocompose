package com.hluck.myquotes.domain.models

data class HomeScreenModel(
    val randomQuote: Quote? = null,
    val allQuotes: List<Quote>? = null
)
