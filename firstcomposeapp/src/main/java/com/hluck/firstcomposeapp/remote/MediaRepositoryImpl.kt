package com.hluck.firstcomposeapp.remote

import com.hluck.firstcomposeapp.toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepositoryImpl @Inject constructor(
    private val mediaApi:api
):MediaRepository {
    override suspend fun getMoviesAndTvSeriesList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        category: String,
        page: Int,
        apiKey: String
    ): Flow<Resource<List<Media>>> {
        return flow {

            emit(Resource.Loading(true))

            var searchPage = page
            if (isRefresh) {
                searchPage = 1
            }

            val remoteMediaList = try {
                val results = mediaApi.getMoviesAndTvSeriesList(
                    type, category, searchPage, apiKey
                ).results
                "请求成功: ${results.size}".toast()
            } catch (e: Exception) {
                e.printStackTrace()
                "${e.message}".toast()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            }
        }
    }
}