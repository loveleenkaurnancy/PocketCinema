package com.pocketcinema.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pocketcinema.data.local.MovieEntity

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val nowPlaying by viewModel.nowPlaying.collectAsState()
    val topRated by viewModel.topRated.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Now Playing", style = MaterialTheme.typography.titleMedium)
        MovieRow(movies = nowPlaying, onMovieClick)

        Spacer(Modifier.height(16.dp))

        Text("Top Rated", style = MaterialTheme.typography.titleMedium)
        MovieRow(movies = topRated, onMovieClick)
    }
}

@Composable
fun MovieRow(movies: List<MovieEntity>, onMovieClick: (Int) -> Unit) {
    LazyRow {
        items(movies) { movie ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = movie.title,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
                Text(movie.title ?: "", maxLines = 1)
            }
        }
    }
}