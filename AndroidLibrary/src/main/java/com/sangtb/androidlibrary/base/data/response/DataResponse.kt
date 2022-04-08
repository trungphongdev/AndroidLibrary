package com.sangtb.androidlibrary.base.data.response

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/

sealed class DataResponse<out T> {
    data class Success<T>(val data : T) : DataResponse<T>()
    data class Fail(val exception : Throwable) : DataResponse<Nothing>()
}