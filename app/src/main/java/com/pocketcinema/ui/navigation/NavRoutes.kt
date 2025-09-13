package com.pocketcinema.ui.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Details : NavRoutes("details/{movieId}") {
        fun createRoute(movieId: Int) = "details/$movieId"
    }
    object Bookmarks : NavRoutes("bookmarks")
}