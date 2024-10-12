package com.hluck.movieinfo.main.domain.repository

import com.hluck.movieinfo.main.domain.models.Media
import com.hluck.movieinfo.util.Resource
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun updateItem(media:Media)

    suspend fun insertItem(media:Media)

    suspend fun getItem(
        id:Int,
        type:String,
        category:String
    ):Media

    suspend fun getMoviesAndTvSeriesList(
        fetchFromRemote:Boolean,
        isRefresh:Boolean,
        type: String,
        category: String,
        page:Int,
        apiKey:String
    ):Flow<Resource<List<Media>>>

    suspend fun getTrendingList(
        fetchFromRemote:Boolean,
        isRefresh:Boolean,
        type: String,
        time: String,
        page:Int,
        apiKey:String
    ):Flow<Resource<List<Media>>>


}