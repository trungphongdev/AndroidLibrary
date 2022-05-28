package com.sangtb.androidlibrary.utils;

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/26/2022
*/


fun Fragment.hideKeyboard(view: View) {
    val inputMethodManager: InputMethodManager? =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showTimePickerDialog(
    formatType: String = "dd/MM/yyyy",
    onReceiveDateTime: (String) -> Unit
) {
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(requireContext(), { _, year, month, date ->
        val calendar = GregorianCalendar()
        calendar.set(Calendar.DAY_OF_MONTH, date)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)

        val viewFormatter = SimpleDateFormat(formatType, Locale.US)
        onReceiveDateTime(
            viewFormatter.format(calendar.time)
        )
    }, year, month, dayOfMonth).show()
}

fun View.showPopUp(menu : Int, action: (Int)->Unit){
    setOnClickListener {
        PopupMenu(context,this).apply {
            inflate(menu)
            setOnMenuItemClickListener {item->
                (it as? TextView)?.let { textView->
                    textView.text = item.title
                    action.invoke(item.itemId)
                }
                (it as? EditText)?.let { editText->
                    editText.setText(item.title)
                    action.invoke(item.itemId)
                }
                true
            }
            show()
        }
    }
}

fun AppCompatActivity.showToast(contentID: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, getString(contentID), duration).show()
}

fun AppCompatActivity.showToast(contentID: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, contentID, duration).show()
}

fun AppCompatActivity.setHideStatusBarAndControlBar(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}

fun Context.getNavigationBarHeight() : Int{
    val resources: Resources = this.resources
    val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}

fun Context.getStatusBarHeight(): Int {
    val resources: Resources = this.resources
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}