package com.test.koinapp.presentation.feature

import android.os.Parcelable
import com.test.koinapp.domain.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int>? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
) : Parcelable