package com.pocketcinema.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movie by viewModel.movie.collectAsState()

    val backgroundColor = Color(0xFF121213)

    LaunchedEffect(movieId) { viewModel.loadMovie(movieId) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        movie?.let { m ->
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${m.backdropPath}",
                    contentDescription = m.title,
                    modifier = Modifier
                        .height(280.dp)
                        .padding(top = 30.dp)
                        .fillMaxSize()
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Spacer(Modifier.height(8.dp))
                    Text(m.title ?: "", style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Spacer(Modifier.height(8.dp))
                    Text(m.overview ?: "", color = Color.Gray)
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = { viewModel.toggleBookmark(m.id, !m.isBookmarked) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF21262D)
                        )
                    ) {
                        Text(if (m.isBookmarked) "Remove Bookmark" else "Bookmark")
                    }
                }
            }
        } ?: run {
            Text("Loading...", color = Color.White)
        }
    }
}