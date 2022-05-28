package com.sangtb.androidlibrary.utils;

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/18/2022
*/
open class SingleTonHolder<T>(private val constructor: () -> T) {
    @Volatile
    private var instance: T? = null

    fun getInstance() = when {
        instance != null -> instance!!
        else -> synchronized(this) {
            if (instance == null) {
                instance = constructor()
            }
            instance!!
        }
    }
}
