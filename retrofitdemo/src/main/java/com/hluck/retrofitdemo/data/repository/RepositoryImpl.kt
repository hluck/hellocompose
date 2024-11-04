package com.hluck.retrofitdemo.data.repository

import com.hluck.retrofitdemo.data.Result
import com.hluck.retrofitdemo.data.api.Api
import com.hluck.retrofitdemo.data.models.ProductX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val api: Api
):ProductsRepository {
    override suspend fun getProductList(): Flow<Result<List<ProductX>>> {
        return flow {
            val result = try {
                api.getProductsList()
            } catch (e:Exception){
                e.printStackTrace()
                emit(Result.Error(message = "请求失败"))
                return@flow
            }
            emit(Result.Success(data = result.products))
        }
    }
}