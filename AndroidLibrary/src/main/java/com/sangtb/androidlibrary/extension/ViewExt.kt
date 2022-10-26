package com.sangtb.androidlibrary.extension

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

fun Context.loadAttrs(attrs: AttributeSet?, attrType: IntArray, function: (TypedArray) -> Unit) {
    if (attrs == null) return
    val a = obtainStyledAttributes(attrs, attrType)
    function(a)
    a.recycle()
}
