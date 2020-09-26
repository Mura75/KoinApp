package com.test.koinapp.domain.repository

import com.test.koinapp.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    suspend fun getMovies(page: Int): Pair<Int, List<Movie>>
    suspend fun getMovie(movieId: Int): Movie
}