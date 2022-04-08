package com.sangtb.androidlibrary.utils;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sangtb.androidlibrary.base.action.IActionDialog
import com.sangtb.androidlibrary.base.data.DataDialog

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/

abstract class DialogLibrary<T : ViewDataBinding> : DialogFragment(),IActionDialog<T> {
    private val _dataDialog = MutableLiveData(DataDialog())

    @get:LayoutRes
    abstract val layout: Int

   private var _binding: T? = null
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

    override val dataDialog: LiveData<DataDialog>
        get() = _dataDialog

    override fun setMessage(message: String): DialogLibrary<T> {
        Log.d(TAG, "setMessage: $message")
        _dataDialog.value?.message = message
        return this
    }

    override fun setTitle(title: String): DialogLibrary<T> {
        Log.d(TAG, "setTitle: $title")
        _dataDialog.value?.title = title
        return this
    }

    override fun setTitleAccept(titleAccept: String): DialogLibrary<T> {
        _dataDialog.value?.titleAccept = titleAccept
        return this
    }

    override fun setTitleCancel(titleCancel: String): DialogLibrary<T> {
        _dataDialog.value?.titleCancel = titleCancel
        return this
    }

    override fun setTypeDialog(type: TypeDialog): DialogLibrary<T> {
        _dataDialog.value?.typeDialog = type
        return this
    }

    companion object{
        private const val TAG = "SangTB"
    }

    override fun onCancel(cancel: () -> Unit) {
        dismiss()
    }
}

enum class TypeDialog{
    Normal,
    TWO_BUTTON,
}
