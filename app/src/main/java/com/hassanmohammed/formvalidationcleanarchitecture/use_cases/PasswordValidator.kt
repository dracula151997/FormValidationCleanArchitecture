package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import com.hassanmohammed.formvalidationcleanarchitecture.R
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PasswordValidator @Inject constructor() : IValidator{

    override fun validate(vararg inputs: String): FormValidationResult {
        if (inputs.size > 1)
            throw IllegalArgumentException()
        val password = inputs.first()
        if (password.isEmpty())
            return FormValidationResult(
                success = false,
                errorMessage = "The password can't be blank",
                errorMessageRes = R.string.error_msg_password_blank
            )

        if (password.length < 8)
            return FormValidationResult(
                success = false,
                errorMessage = "The password needs to consist of at least 8 characters",
                errorMessageRes = R.string.error_msg_password_length
            )

        val containsLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits)
            return FormValidationResult(
                success = false,
                errorMessage = "The password needs to contain at least one letter and digit",
                errorMessageRes = R.string.error_msg_password_letter_and_digit
            )


        return FormValidationResult(
            success = true
        )
    }
}