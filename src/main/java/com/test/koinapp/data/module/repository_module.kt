package com.test.koinapp.data.module

import com.test.koinapp.data.mapper.MovieMapper
import com.test.koinapp.data.repository.MovieRepositoryImpl
import com.test.koinapp.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MovieMapper() }
    single { MovieRepositoryImpl(movieApi = get(), movieMapper = get()) as MovieRepository }
}