package com.test.koinapp.data.model

import com.google.gson.annotations.SerializedName

data class GenreData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
