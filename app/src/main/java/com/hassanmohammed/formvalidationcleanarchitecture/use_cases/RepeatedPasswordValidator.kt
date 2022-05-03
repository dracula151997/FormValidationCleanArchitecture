package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import com.hassanmohammed.formvalidationcleanarchitecture.R
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepeatedPasswordValidator @Inject constructor() : IValidator {
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

    override fun validate(vararg inputs: String): FormValidationResult {
        if (inputs.size != 2)
            throw IllegalArgumentException()

        val password = inputs.first()
        val repeatedPassword = inputs.last()
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