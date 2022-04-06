package com.sangtb.androidlibrary.utils;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.sangtb.androidlibrary.base.action.IActionDialog
import com.sangtb.androidlibrary.base.data.DataDialog

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/

abstract class DialogLibrary<T : ViewDataBinding> : DialogFragment(),IActionDialog<T> {

    @get:LayoutRes
    abstract val layout: Int

    var _binding: T? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  DataBindingUtil.inflate(inflater,layout,container,false)
        return _binding!!.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override val binding: T
        get() = _binding!!

    override val dataDialog: DataDialog
        get() = DataDialog()

    override fun setMessage(message: String): DialogLibrary<T> {
        dataDialog.message = message
        return this
    }

    override fun setTitle(title: String): DialogLibrary<T> {
        dataDialog.title = title
        return this
    }

    override fun setTitleAccept(titleAccept: String): DialogLibrary<T> {
        dataDialog.titleAccept = titleAccept
        return this
    }

    override fun setTitleCancel(titleCancel: String): DialogLibrary<T> {
        dataDialog.titleCancel = titleCancel
        return this
    }

    override fun setTypeDialog(type: TypeDialog): DialogLibrary<T> {
        dataDialog.typeDialog = type
        return this
    }

}

enum class TypeDialog{
    Normal,
    TWO_BUTTON,
}
