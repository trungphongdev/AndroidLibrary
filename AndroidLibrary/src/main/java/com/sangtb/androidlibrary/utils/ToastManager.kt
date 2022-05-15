package com.sangtb.androidlibrary.utils

import com.sangtb.game.utils.SingleLiveEvent

class ToastManager private constructor(){

    val errorThrowable = SingleLiveEvent<Throwable>()

    fun showErrorThrowable(throwable: Throwable){
        errorThrowable.postValue(throwable)
    }

    private object Holder { val INSTANCE = ToastManager()}

    companion object{
        @JvmStatic
        fun getInstance(): ToastManager{
            return Holder.INSTANCE
        }
    }
}