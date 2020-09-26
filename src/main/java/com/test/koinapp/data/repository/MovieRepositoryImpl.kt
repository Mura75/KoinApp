package com.test.koinapp.data.repository

import com.test.koinapp.data.mapper.MovieMapper
import com.test.koinapp.data.network.MovieApi
import com.test.koinapp.domain.model.Movie
import com.test.koinapp.domain.repository.MovieRepository
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovies(page: Int): Pair<Int, List<Movie>> = withContext(Dispatchers.IO) {
        val response = movieApi.getPopularMovies(page)
        if (response.isSuccessful) {
            val pages = response.body()?.totalPages ?: 0
            val list = response.body()?.results?.map { movieMapper.to(it) } ?: emptyList()
            val pair = Pair(pages, list)
            pair
        } else {
            throw Throwable(response.errorBody()?.toString())
        }
    }

    override suspend fun getMovie(movieId: Int): Movie = withContext(Dispatchers.IO) {
        val response = movieApi.getMovie(movieId)
        if (response.isSuccessful) {
            val result = response.body() ?: throw Throwable("movie doesn't exist")
            movieMapper.to(model = result)
        } else {
            throw Throwable(response.errorBody()?.toString())
        }
    }
}