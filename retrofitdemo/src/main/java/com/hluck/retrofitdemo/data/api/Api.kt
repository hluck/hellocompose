package com.hluck.retrofitdemo.data.api

import com.hluck.retrofitdemo.data.models.Product
import retrofit2.http.GET

interface Api {

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }

    @GET("products")
    suspend fun getProductsList():Product

}