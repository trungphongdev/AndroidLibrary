package com.sangtb.androidlibrary.base.data.repository

import android.util.Log
import com.sangtb.androidlibrary.utils.ToastManager
import kotlinx.coroutines.*

abstract class BaseRepository {
    protected val TAG = this.javaClass.name
    protected val toastManager = ToastManager.getInstance()
    private var jog: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    protected open fun callApi(method: suspend () -> Unit) {
        jog = coroutineScope.launch {
            try {
                toastManager.loadingDialog.postValue(true)
                method.invoke()
            }catch (e : Throwable){
                Log.d(TAG, "callApi exception: ${e.message}")
                toastManager.errorThrowable.postValue(e)
            }finally {
                toastManager.loadingDialog.postValue(false)
            }
        }
    }

    protected open fun cancelCoroutine(){
        jog?.cancel()
        jog = null
    }

    open suspend fun <T> safeApiCall(apiCall: suspend () -> T): T? {
        toastManager.loadingDialog.postValue(true)
        return withContext(Dispatchers.IO) {
            try {
                return@withContext apiCall.invoke()
            } catch (e: Throwable) {
                Log.d(TAG, "safeApiCall: ${e.message.toString()}")
                toastManager.errorThrowable.postValue(e)
                null
            }finally {
                toastManager.loadingDialog.postValue(false)
            }
        }
    }

    open suspend fun <T> safeApiCall(vararg apiCall: suspend()->T): List<Pair<Int, T>>? {
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
                null
            }finally {
                toastManager.loadingDialog.postValue(false)
                coroutineContext.cancelChildren()
            }
        }
    }
}
