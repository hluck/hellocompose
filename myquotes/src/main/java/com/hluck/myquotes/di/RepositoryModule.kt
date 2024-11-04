package com.hluck.myquotes.di

import com.hluck.myquotes.data.remote.Api
import com.hluck.myquotes.domain.repository.QuoteRepository
import com.hluck.myquotes.data.repositoryImpl.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun getQuoteRepositoryImpl(api:Api):QuoteRepository = QuoteRepositoryImpl(api)
}