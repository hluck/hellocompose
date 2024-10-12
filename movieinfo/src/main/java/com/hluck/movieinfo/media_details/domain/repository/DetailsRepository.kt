package com.hluck.movieinfo.media_details.domain.repository

import com.hluck.movieinfo.main.domain.models.Media
import com.hluck.movieinfo.util.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun getDetails(
        type:String,
        isRefresh:Boolean,
        id:Int,
        apiKey:String,
    ):Flow<Resource<Media>>
}