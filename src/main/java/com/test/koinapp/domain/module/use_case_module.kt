package com.test.koinapp.domain.module

import com.test.koinapp.domain.use_cases.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMoviesUseCase(movieRepository = get()) }
}