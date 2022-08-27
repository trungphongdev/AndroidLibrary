package com.sangtb.androidlibrary.base.action

import android.view.View

interface IActivityApplication {
    var rootView: View?
    val permissions: List<String>?
        get() = null
}