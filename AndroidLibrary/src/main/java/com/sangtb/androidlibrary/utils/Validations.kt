package com.sangtb.androidlibrary.utils;

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import com.sangtb.androidlibrary.R
import java.util.regex.Pattern

class Validations () {
    fun isEmailValid(email: String): Boolean {
        val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
        val inputStr: CharSequence = email
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val PASSWORD_PATTERN = "^[a-z0-9]{6,12}$"
        val inputStr: CharSequence = password
        val pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }

    fun isValidPhoneNumber(number: String?): Boolean {
        val validNumber = "^0[35789]{1}\\d{8}$"
        val pattern = Pattern.compile(validNumber, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(number)
        val validNumber1 = "^(([+84]{3})|[84]{2})[35789]{1}\\d{8}$"
        val pattern1 = Pattern.compile(validNumber1, Pattern.CASE_INSENSITIVE)
        val matcher1 = pattern1.matcher(number)
        val validNumber3 = "^[35789]{1}\\d{8}$"
        val pattern3 = Pattern.compile(validNumber3, Pattern.CASE_INSENSITIVE)
        val matcher3 = pattern3.matcher(number)
        return (matcher.find() || matcher1.find() || matcher3.find())
    }

    fun isValidName(s: String): Boolean {
        val validName = "[0-9]"
        val pattern = Pattern.compile(validName, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(s)
        return matcher.find() || isValidSpecialCharacters(s)
    }

    fun checkInternet(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connMgr.isDefaultNetworkActive
    }

//    fun isPermissionGrand(list: Array<String>): Boolean {
//        for (i in list) {
//            if (ActivityCompat.checkSelfPermission(
//                    _context,
//                    i
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                return false
//            }
//        }
//        return true
//    }

//    fun checkGpsStatus(): Boolean {
//        val locationManager = _context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//    }

    private fun isValidSpecialCharacters(s: String?): Boolean {
        val regex = Pattern.compile("[$&+,:;=\\?@#|/'<>.^*()%!-]") //~`•√ππ÷×¶∆\}{°¢€£©®™✓
        return regex.matcher(s).find()
    }

    fun isValidAddress(s: String): Boolean {
        val regex = Pattern.compile("[$&+:;=\\?@#|/'<>.^*()%!]") //~`•√ππ÷×¶∆\}{°¢€£©®™✓
        return regex.matcher(s).find() || s.isEmpty()
    }

    fun replaceMultiple(baseString: String, vararg replaceParts: String): String {
        var baseString = baseString
        for (s in replaceParts) {
            baseString = baseString.replace(s.toRegex(), "")
        }
        return baseString
    }

    fun hideKeyboard(v: View, context: Context) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.applicationWindowToken, 0)
    }
}

