package com.hassanmohammed.formvalidationcleanarchitecture.di

import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.EmailValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.PasswordValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers.RepeatedPasswordValidatorQualifier
import com.hassanmohammed.formvalidationcleanarchitecture.use_cases.EmailValidator
import com.hassanmohammed.formvalidationcleanarchitecture.use_cases.IValidator
import com.hassanmohammed.formvalidationcleanarchitecture.use_cases.PasswordValidator
import com.hassanmohammed.formvalidationcleanarchitecture.use_cases.RepeatedPasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ValidationUseCaseModule {
    @ViewModelScoped
    @Provides
    @EmailValidatorQualifier
    fun provideEmailValidator() : IValidator = EmailValidator()

    @ViewModelScoped
    @Provides
    @PasswordValidatorQualifier
    fun providePasswordValidator() : IValidator = PasswordValidator()

    @ViewModelScoped
    @Provides
    @RepeatedPasswordValidatorQualifier
    fun provideRepeatedPasswordValidator() : IValidator = RepeatedPasswordValidator()
}