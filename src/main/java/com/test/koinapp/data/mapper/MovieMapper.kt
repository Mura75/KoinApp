package com.test.koinapp.data.mapper

import com.test.koinapp.data.model.GenreData
import com.test.koinapp.data.model.MovieData
import com.test.koinapp.domain.Mapper
import com.test.koinapp.domain.model.Genre
import com.test.koinapp.domain.model.Movie

class MovieMapper : Mapper<Movie, MovieData> {

    override fun from(model: Movie): MovieData = with(model) {
        return MovieData(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            genres = genres?.map { GenreData(it.id, it.name) },
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

    override fun to(model: MovieData): Movie = with(model) {
        return Movie(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            genres = genres?.map { Genre(it.id, it.name) },
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
