package com.pocketcinema.data.remote

data class PagedMovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_results: Int,
    val total_pages: Int
)