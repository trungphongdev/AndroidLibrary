package com.sangtb.androidlibrary.base.action;

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sangtb.androidlibrary.base.data.DataDialog
import com.sangtb.androidlibrary.utils.DialogLibrary
import com.sangtb.androidlibrary.utils.TypeDialog

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/

interface IActionDialog<T : ViewDataBinding> {
    val dataDialog: LiveData<DataDialog>
    val binding: T
    val viewModel: AndroidViewModel?
        get() = null

    fun setTitle(title: String): DialogLibrary<T>
    fun setMessage(message: String): DialogLibrary<T>
    fun setTitleAccept(titleAccept: String): DialogLibrary<T>
    fun setTitleCancel(titleCancel: String): DialogLibrary<T>
    fun setTypeDialog(type: TypeDialog): DialogLibrary<T>
    fun onCancel()
    fun onAccept()
}
