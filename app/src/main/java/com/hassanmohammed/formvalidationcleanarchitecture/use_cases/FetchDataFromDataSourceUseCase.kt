package com.hassanmohammed.formvalidationcleanarchitecture.use_cases

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchDataFromDataSourceUseCase @Inject constructor() {
    operator fun invoke() = flow {
        delay(1000)
        emit("Success")
    }
}