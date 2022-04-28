package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import com.hassanmohammed.formvalidationcleanarchitecture.R
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepeatedPasswordValidator @Inject constructor() {
    fun validate(password: String, repeatedPassword: String): FormValidationResult {
        if (password != repeatedPassword)
            return FormValidationResult(
                success = false,
                errorMessage = "Passwords don't match",
                errorMessageRes = R.string.error_msg_passwords_donot_match
            )

        return FormValidationResult(
            success = true
        )
    }
}