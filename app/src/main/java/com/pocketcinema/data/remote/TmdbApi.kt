package com.pocketcinema.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// DTOs will be provided below
interface TmdbApi {

    // Top rated movies (you gave curl for top_rated)
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PagedMovieResponse

    // Now playing movies
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PagedMovieResponse

    // Movie details
    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("language") language: String = "en-US"
    ): MovieDto

    // (We will add search/trending later if you want)
}