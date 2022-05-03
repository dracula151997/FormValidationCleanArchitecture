package com.hassanmohammed.formvalidationcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.EmailValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.PasswordValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.RepeatedPasswordValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @EmailValidatorQualifier private val emailValidator: IValidator,
    @PasswordValidatorQualifier private val passwordValidator: IValidator,
    @RepeatedPasswordValidatorQualifier private val repeatedPasswordValidator: IValidator,
    private val fetchDataFromDataSourceUseCase: FetchDataFromDataSourceUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        RegistrationFormValidateState()
    )
    val formValidationState = _state.asStateFlow()

    private val _response = MutableSharedFlow<String>()
    val responseFlow = _response.asSharedFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> _state.value = formValidationState.value.copy(
                email = event.email
            )
            is RegistrationFormEvent.PasswordChanged -> _state.value =
                formValidationState.value.copy(
                    password = event.password
                )
            is RegistrationFormEvent.RepeatedPasswordChanged -> _state.value =
                formValidationState.value.copy(
                    repeatedPassword = event.repeatedPassword
                )
            RegistrationFormEvent.Submit -> submitData()
        }
    }

    fun onEmailChanged(email: String) {
        _state.value = formValidationState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun onPasswordChanged(password: String) {
        _state.value = formValidationState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun onRepeatedPasswordChanged(repeatedPassword: String) {
        _state.value = formValidationState.value.copy(
            repeatedPassword = repeatedPassword,
            repeatedPasswordError = null
        )
    }

    fun onSubmit() = submitData()

    private fun submitData() {
        val emailValidated = emailValidator.validate(formValidationState.value.email)
        val passwordValidated = passwordValidator.validate(formValidationState.value.password)
        val repeatedPasswordValidated = repeatedPasswordValidator.validate(
            formValidationState.value.password,
            formValidationState.value.repeatedPassword
        )

        val hasError = listOf(
            emailValidated,
            passwordValidated,
            repeatedPasswordValidated
        ).any { !it.success }

        if (hasError) {
            _state.value = formValidationState.value.copy(
                emailError = emailValidated.errorMessage,
                passwordError = passwordValidated.errorMessage,
                repeatedPasswordError = repeatedPasswordValidated.errorMessage
            )
            return
        } else {
            _state.value = formValidationState.value.copy(
                emailError = null,
                passwordError = null,
                repeatedPasswordError = null
            )
        }
        viewModelScope.launch {
            fetchDataFromDataSourceUseCase()
                .onEach {
                    _response.emit(it)
                }.launchIn(this)
        }
    }
}