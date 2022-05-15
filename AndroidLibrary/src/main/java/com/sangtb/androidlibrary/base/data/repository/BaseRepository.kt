package com.sangtb.androidlibrary.base.data.repository

import com.sangtb.androidlibrary.utils.ToastManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    private val repository = ToastManager.getInstance()

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                repository.showErrorThrowable(throwable)
                Result.failure(throwable)
            }
        }
    }
}
