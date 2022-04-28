package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import androidx.annotation.StringRes

data class FormValidationResult(
    val success: Boolean,
    val errorMessage: String? = null,
    @StringRes val errorMessageRes: Int? = null
)