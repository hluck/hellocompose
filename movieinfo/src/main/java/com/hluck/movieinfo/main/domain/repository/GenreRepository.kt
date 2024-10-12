package com.hluck.movieinfo.main.domain.repository

import com.hluck.movieinfo.main.domain.models.Genre
import com.hluck.movieinfo.util.Resource
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenres(
        fetchFromRemote: Boolean,
        type: String,
        apiKey: String
    ): Flow<Resource<List<Genre>>>
}