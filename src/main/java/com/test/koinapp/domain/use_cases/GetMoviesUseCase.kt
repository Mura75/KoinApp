package com.test.koinapp.domain.use_cases

import com.test.koinapp.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovieList(page: Int) = movieRepository.getMovies(page)
}