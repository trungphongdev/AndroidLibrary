package com.sangtb.androidlibrary.base.data.repository

import com.sangtb.androidlibrary.utils.ToastManager
import kotlinx.coroutines.*

abstract class BaseRepository {
    protected val toastManager = ToastManager.getInstance()
    private var jog: Job? = null

    private val coroutineScope =
        CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            toastManager.errorThrowable.postValue(throwable)
            toastManager.loadingDialog.postValue(false)
            jog?.cancel()
        })

    protected fun callApi(method: suspend () -> Unit) {
        jog = coroutineScope.launch {
            toastManager.loadingDialog.postValue(true)
            method.invoke()
            toastManager.loadingDialog.postValue(false)
            cancel()
        }
    }

    protected fun cancelCoroutine(){
        jog?.cancel()
        jog = null
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): T? {
        toastManager.loadingDialog.postValue(true)
        return withContext(Dispatchers.IO) {
            try {
                apiCall.invoke()
            } catch (e: Throwable) {
                toastManager.errorThrowable.postValue(e)
                toastManager.loadingDialog.postValue(false)
                null
            }
        }
    }

    suspend fun <T> safeApiCall(vararg apiCall: suspend()->T): List<Pair<Int, T>>? {
        toastManager.loadingDialog.postValue(true)

        return withContext(Dispatchers.IO) {
            val runningTasks = apiCall.mapIndexed { index, function ->
                async {
                    val apiResponse = function.invoke()
                    index to apiResponse
                }
            }
            try {
                runningTasks.awaitAll()
            } catch (e: Throwable) {
                toastManager.errorThrowable.postValue(e)
                toastManager.loadingDialog.postValue(false)
                coroutineContext.cancelChildren()
                null
            }
        }
    }
}
