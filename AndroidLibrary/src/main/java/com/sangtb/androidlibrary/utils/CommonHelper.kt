package com.sangtb.androidlibrary.utils

import android.content.Context
import android.widget.Toast

object CommonHelper {
    fun makeToast(context: Context?, resID:Int?, duration: Int): Toast? =
        resID?.let{
            Toast.makeText(context, resID, duration)
        }

    fun makeToast(context: Context?, text:String?, duration: Int): Toast? =
        text?.let {
            Toast.makeText(context, text, duration)
        }
}