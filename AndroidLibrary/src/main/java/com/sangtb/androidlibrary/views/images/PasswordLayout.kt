package com.sangtb.androidlibrary.views.images

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.sangtb.androidlibrary.R
import com.sangtb.androidlibrary.databinding.EdtPasswordInputBinding
import com.sangtb.androidlibrary.extension.loadAttrs

// Template dành cho custom View
class PasswordLayout(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    val text : String
        get() = binding.edtPassword.text.toString()
    fun getEdtView() = binding.edtPassword

    private val binding by lazy {
        EdtPasswordInputBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var hint = DEFAULT_STRING

    init {
        context.loadAttrs(attributeSet, R.styleable.PasswordLayout) {
            hint = it.getString(R.styleable.PasswordLayout_hint) ?: DEFAULT_STRING
        }

        setupListener()
        binding.edtPassword.hint = hint
    }

    private fun setupListener() {
        with(binding) {
            txtPassActionShow.setOnClickListener {
                isActivated = !isActivated
                txtPassActionShow.text = context.getString( if(isActivated) R.string.hide  else R.string.show)
                edtPassword
                    .showPassword(isActivated)
                    .seekCursorToLast()
            }
        }
    }

    companion object {
        const val DEFAULT_STRING = ""
    }
}

private fun EditText.seekCursorToLast() {
    setSelection(length())
}

private fun EditText.showPassword(activated: Boolean): EditText {
    transformationMethod = if (!activated) PasswordTransformationMethod.getInstance()
    else HideReturnsTransformationMethod.getInstance()
    return this
}
