package com.hluck.firstcomposeapp.remote

import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun getMoviesAndTvSeriesList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        category: String,
        page: Int,
        apiKey: String
    ): Flow<Resource<List<Media>>>
}