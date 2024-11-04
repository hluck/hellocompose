package com.hluck.retrofitdemo.data.repository

import com.hluck.retrofitdemo.data.Result
import com.hluck.retrofitdemo.data.models.ProductX
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductList():Flow<Result<List<ProductX>>>
}