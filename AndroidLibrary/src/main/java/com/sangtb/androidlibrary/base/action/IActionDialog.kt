package com.sangtb.androidlibrary.base.action;

import androidx.databinding.ViewDataBinding
import com.sangtb.androidlibrary.base.data.DataDialog
import com.sangtb.androidlibrary.utils.DialogLibrary
import com.sangtb.androidlibrary.utils.TypeDialog

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/
interface IActionDialog<T : ViewDataBinding> {
    val dataDialog : DataDialog
    val binding : T

    fun setTitle(title : String) : DialogLibrary<T>
    fun setMessage(message : String) : DialogLibrary<T>
    fun setTitleAccept(titleAccept : String) : DialogLibrary<T>
    fun setTitleCancel(titleCancel : String) : DialogLibrary<T>
    fun setTypeDialog(type : TypeDialog) : DialogLibrary<T>
    fun onCancel(cancel: ()->Unit)
    fun onAccept(accept: ()->Unit)
}