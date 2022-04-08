package com.sangtb.androidlibrary.base

import androidx.recyclerview.widget.DiffUtil

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/

class BaseDiffCallBack<T>(
    private val oldTask: List<T>,
    private val newTask: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldTask.size
    override fun getNewListSize(): Int = newTask.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTask[oldItemPosition].hashCode() == newTask[newItemPosition].hashCode()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTask[oldItemPosition] == newTask[newItemPosition]
    }
}