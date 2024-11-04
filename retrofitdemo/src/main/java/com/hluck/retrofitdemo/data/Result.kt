package com.hluck.retrofitdemo.data

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null) : Result<T>(data)
    class Error<T>(data: T? = null, message: String):Result<T>(data,message)
}