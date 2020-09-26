package com.test.koinapp.presentation.feature

import com.test.koinapp.data.model.GenreData
import com.test.koinapp.data.model.MovieData
import com.test.koinapp.domain.Mapper
import com.test.koinapp.domain.model.Movie

class MovieModelMapper : Mapper<Movie, MovieModel> {

    override fun from(model: Movie): MovieModel = with(model) {
        return MovieModel(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            //genres = genres?.map { GenreData(it.id, it.name) },
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

    override fun to(model: MovieModel): Movie = with(model) {
        return Movie(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            genres = emptyList(), //genres?.map { GenreData(it.id, it.name) },
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

}