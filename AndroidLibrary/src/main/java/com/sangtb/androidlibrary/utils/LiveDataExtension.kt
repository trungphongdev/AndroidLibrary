package com.sangtb.androidlibrary.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <T1, T2, R> LiveData<T1>.combine(
    liveData2: LiveData<T2>,
    crossinline transform: (T1, T2) -> R
): LiveData<R> = combineLiveData(this, liveData2) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2
    )
}


inline fun <T1, T2, T3, R> LiveData<T1>.combine(
    liveData2: LiveData<T2>,
    liveData3: LiveData<T3>,
    crossinline transform: (T1, T2, T3) -> R
): LiveData<R> = combineLiveData(this, liveData2, liveData3) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3
    )
}


inline fun <R> combineLiveData(
    vararg varargLiveData: LiveData<*>,
    crossinline transform: (Array<*>) -> R
) = MediatorLiveData<R>().apply {
    varargLiveData.forEach { liveData ->
        addSource(liveData) {
            val listDataCallback = varargLiveData.map {
                it.value ?: return@addSource
            }.toTypedArray()
            value = transform(listDataCallback)
        }
    }
}
