package com.sangtb.androidlibrary.utils

class ToastManager private constructor(){

    val errorThrowable = SingleLiveEvent<Throwable>()
    val loadingDialog = SingleLiveEvent<Boolean>()

    private object Holder { val INSTANCE = ToastManager()}

    companion object{
        @JvmStatic
        fun getInstance(): ToastManager{
            return Holder.INSTANCE
        }
    }
}