package com.pocketcinema.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    val title: String?,
    val overview: String?,
    @SerializedName("poster_path") val poster_path: String?,
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("release_date") val release_date: String?,
    @SerializedName("vote_average") val vote_average: String?
)