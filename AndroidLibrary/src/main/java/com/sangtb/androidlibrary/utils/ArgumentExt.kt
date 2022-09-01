package com.sangtb.androidlibrary.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * Create new bundle with key = class name
 */
fun <T : Serializable> T.toBundle(): Bundle {
    val bundle = Bundle()
    bundle.putSerializable(this.javaClass.name, this)
    return bundle
}

/**
 * Retrieve property from intent
 */
inline fun <reified T : Any> Fragment.argument() = lazy { arguments?.get(T::class.java.name) as T }
inline fun <reified T : Serializable> Fragment.arguments() =  arguments?.getSerializable(T::class.java.name) as T