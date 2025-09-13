package com.pocketcinema.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movie by viewModel.movie.collectAsState()

    LaunchedEffect(movieId) { viewModel.loadMovie(movieId) }

    movie?.let { m ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${m.posterPath}",
                contentDescription = m.title,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Text(m.title ?: "", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(4.dp))
            Text(m.overview ?: "")
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { viewModel.toggleBookmark(m.id, !m.isBookmarked) }
            ) {
                Text(if (m.isBookmarked) "Remove Bookmark" else "Bookmark")
            }
        }
    } ?: run {
        Text("Loading...")
    }
}