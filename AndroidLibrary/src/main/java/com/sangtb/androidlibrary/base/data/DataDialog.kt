package com.sangtb.androidlibrary.base.data

import com.sangtb.androidlibrary.utils.TypeDialog

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/6/2022
*/
data class DataDialog(
    var title: String? = "",
    var message: String? = "",
    var titleAccept: String? = "",
    var titleCancel: String? = "",
    var typeDialog: TypeDialog? = TypeDialog.Normal,
)
