package com.sangtb.androidlibrary.utils;

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sangtb.androidlibrary.R
import java.util.regex.Pattern


object Validator {
    fun List<Pair<EditText, Boolean>>.isValid(): Boolean {
        var isValid = true
        val context = this[0].first.context
        this.forEach {
            val view = it.first
            val isNullCheck = it.second
            if (isNullCheck && view.text.isNullOrEmpty()) {
                isValid = false
                it.first.error = context.getString(R.string.null_error)
                return@forEach
            }
            if (!view.error.isNullOrEmpty()) return false
        }
        return isValid
    }

    fun EditText.validate(
        listValidator: List<(String) -> Int?>? = null,
        isRequireEmptyCheck: Boolean = true
    ): Pair<EditText, Boolean> {
        this.doOnTextChanged { text, _, _, _ ->
            listValidator?.let {
                it.forEach { validate ->
                    validate(text.toString())?.let { errorStringID ->
                        this.error = context.getString(errorStringID)
                        return@doOnTextChanged
                    }
                }
            }
        }
        return Pair(this, isRequireEmptyCheck)
    }

    fun isEmailValid(email: String): Int? {
        val regExpn = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
        val inputStr: CharSequence = email
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        return if (matcher.matches()) null else R.string.email_error
    }

    fun isPasswordValid(password: String): Int? {
        val PASSWORD_PATTERN = "^[a-z0-9]{6,12}$"
        val inputStr: CharSequence = password
        val pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(inputStr)
        return if (matcher.matches()) null else R.string.password_error
    }

    fun isRequireNotNull(text: String): Int? {
        return if (text.isNotEmpty()) null else R.string.null_error
    }

    fun isValidPhoneNumber(number: String?): Int? {
        val validNumber = "^0[35789]{1}\\d{8}$"
        val pattern = Pattern.compile(validNumber, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(number)
        val validNumber1 = "^(([+84]{3})|[84]{2})[35789]{1}\\d{8}$"
        val pattern1 = Pattern.compile(validNumber1, Pattern.CASE_INSENSITIVE)
        val matcher1 = pattern1.matcher(number)
        val validNumber3 = "^[35789]{1}\\d{8}$"
        val pattern3 = Pattern.compile(validNumber3, Pattern.CASE_INSENSITIVE)
        val matcher3 = pattern3.matcher(number)
        return if(matcher.find() || matcher1.find() || matcher3.find()) null else R.string.phone_error
    }

    fun isValidName(s: String): Int? {
        val validName = "[0-9]"
        val pattern = Pattern.compile(validName, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(s)
        return if(matcher.find() || isValidSpecialCharacters(s)) R.string.name_is_not_valid else null
    }

    private fun isValidSpecialCharacters(s: String?): Boolean {
        val regex = Pattern.compile("[$&+,:;=\\?@#|/'<>.^*()%!-]") //~`•√ππ÷×¶∆\}{°¢€£©®™✓
        return regex.matcher(s).find()
    }

    fun isValidAddress(s: String): Int? {
        val regex = Pattern.compile("[$&+:;=\\?@#|/'<>.^*()%!]") //~`•√ππ÷×¶∆\}{°¢€£©®™✓
        return if(regex.matcher(s).find() || s.isEmpty()) R.string.address_error else null
    }

}


