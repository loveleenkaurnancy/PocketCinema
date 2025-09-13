package com.pocketcinema.ui.bookmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun BookmarkScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val movies = viewModel.bookmarkedMovies.collectAsState().value

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(movies) { movie ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onMovieClick(movie.id) }
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = movie.title,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(movie.title ?: "", style = MaterialTheme.typography.titleMedium)
                    Text(movie.releaseDate ?: "", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}