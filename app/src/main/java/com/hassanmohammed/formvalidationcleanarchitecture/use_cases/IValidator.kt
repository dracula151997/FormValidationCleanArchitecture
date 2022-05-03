package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

interface IValidator {
    fun validate(vararg inputs: String) : FormValidationResult
}