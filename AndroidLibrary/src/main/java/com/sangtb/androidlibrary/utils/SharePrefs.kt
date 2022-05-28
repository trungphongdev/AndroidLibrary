package com.sangtb.androidlibrary.utils

import android.content.SharedPreferences

abstract class SharePrefs{
    protected  abstract val sharedPref: SharedPreferences
    protected abstract val editor: SharedPreferences.Editor

     fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()

        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

     fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, EMPTY)
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, FLOAT_DEFAULT)
            Double::class.java -> sharedPref.getFloat(key, FLOAT_DEFAULT)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    companion object{
        const val EMPTY = ""
        private const val FLOAT_DEFAULT = -1f
    }
}