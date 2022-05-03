package com.hassanmohammed.formvalidationcleanarchitecture.presentation

import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:setTextFieldError")
fun TextInputLayout.bindTextFieldError(error: String?) {
    error?.let { this.error = it }
}

@BindingAdapter("bind:removeErrorWhenTyping")
fun TextInputLayout.bindRemoveErrorWhenTyping(removeError: Boolean){
    this.editText?.doOnTextChanged { text, start, before, count ->
        this.isErrorEnabled = false
        error = null
    }
}