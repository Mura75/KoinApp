package com.test.koinapp.presentation.module

import com.test.koinapp.presentation.feature.MovieListViewModel
import com.test.koinapp.presentation.feature.MovieModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(moviesUseCase = get()) }
}

val mapperModule = module {
    factory { MovieModelMapper() }
}