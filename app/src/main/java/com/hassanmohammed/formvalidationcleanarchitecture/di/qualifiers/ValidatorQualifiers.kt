package com.hassanmohammed.formvalidationcleanarchitecture.di.qualifiers

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmailValidatorQualifier()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PasswordValidatorQualifier()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RepeatedPasswordValidatorQualifier()
