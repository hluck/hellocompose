package com.hluck.movieinfo.media_details.domain.repository

import com.hluck.movieinfo.main.domain.models.Media
import com.hluck.movieinfo.media_details.domain.models.Cast
import com.hluck.movieinfo.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExtraDetailsRepository {
    suspend fun getSimilarMediaList(
        isRefresh: Boolean,
        type: String,
        id: Int,
        page: Int,
        apiKey: String
    ): Flow<Resource<List<Media>>>

    suspend fun getCastList(
        isRefresh: Boolean,
        id: Int,
        apiKey: String
    ): Flow<Resource<Cast>>

    suspend fun getVideosList(
        isRefresh: Boolean,
        id: Int,
        apiKey: String
    ): Flow<Resource<List<String>>>
}