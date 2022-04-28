package com.hassanmohammed.formvalidationcleanarchitecture.presentation

data class RegistrationFormValidateState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
) {
}