package com.sangtb.androidlibrary.base.data.response

sealed class DataResponse<out T> {
    data class Success<T>(val data : T) : DataResponse<T>()
    data class Fail(val exception : Throwable) : DataResponse<Nothing>()
}