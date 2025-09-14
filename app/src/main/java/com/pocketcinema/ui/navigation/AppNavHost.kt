package com.pocketcinema.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pocketcinema.ui.bookmark.BookmarkScreen
import com.pocketcinema.ui.details.MovieDetailScreen
import com.pocketcinema.ui.home.HomeScreen

@Composable
fun PocketCinemaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate(NavRoutes.Details.createRoute(movieId))
                },
                onBookmarksClick = {
                    navController.navigate(NavRoutes.Bookmarks.route)
                }
            )
        }

        composable(
            NavRoutes.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(movieId = movieId)
        }

        composable(NavRoutes.Bookmarks.route) {
            BookmarkScreen(
                onMovieClick = { movieId ->
                    navController.navigate(NavRoutes.Details.createRoute(movieId))
                }
            )
        }
    }
}