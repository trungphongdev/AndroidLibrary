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

interface IActionDialog<T : ViewDataBinding, I> {
    val dataDialog: LiveData<DataDialog>
    val binding: T
    val viewModel: AndroidViewModel?
        get() = null
    val listener: I?
        get() = null

    fun setTitle(title: String): DialogLibrary<T, I>
    fun setMessage(message: String): DialogLibrary<T, I>
    fun setTitleAccept(titleAccept: String): DialogLibrary<T, I>
    fun setTitleCancel(titleCancel: String): DialogLibrary<T, I>
    fun setTypeDialog(type: TypeDialog): DialogLibrary<T, I>
    fun onCancel()
    fun onAccept()
}
