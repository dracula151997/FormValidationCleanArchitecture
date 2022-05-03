package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import androidx.core.util.PatternsCompat
import com.hassanmohammed.formvalidationcleanarchitecture.R
import dagger.hilt.android.scopes.ViewModelScoped
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ViewModelScoped
class EmailValidator @Inject constructor() : IValidator {

    override fun validate(vararg inputs: String): FormValidationResult {
        if (inputs.size > 1)
            throw IllegalArgumentException()

        val email = inputs.first()
        if (email.isEmpty())
            return FormValidationResult(
                success = false,
                errorMessage = "The email can't be blank",
                errorMessageRes = R.string.error_msg_email_blank
            )

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            return FormValidationResult(
                success = false,
                errorMessage = "That's not a valid email",
                errorMessageRes = R.string.error_msg_email_not_valid
            )

        return FormValidationResult(
            success = true
        )
    }
}