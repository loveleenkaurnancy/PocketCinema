package com.pocketcinema.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pocketcinema.R
import com.pocketcinema.data.local.MovieEntity

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit,
    onBookmarksClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val nowPlaying by viewModel.nowPlaying.collectAsState()
    val topRated by viewModel.topRated.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121213))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(top = 50.dp, bottom = 20.dp)
            )

            IconButton(
                onClick = onBookmarksClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Bookmarks",
                    tint = Color.White
                )
            }
        }

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.searchMovies(query)
            },
            placeholder = { Text("Search movies...", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF121213),
                unfocusedContainerColor = Color(0xFF121213)
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        if (query.isNotBlank()) {
            Text(
                text = "Search Results",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            MovieRow(movies = searchResults, onMovieClick = onMovieClick)
        } else {
            if (nowPlaying.isNotEmpty()) {
                Text(
                    text = "Now Playing",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                MovieRow(movies = nowPlaying, onMovieClick = onMovieClick)
                Spacer(Modifier.height(24.dp))
            }

            if (topRated.isNotEmpty()) {
                Text(
                    text = "Top Rated",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                MovieRow(movies = topRated, onMovieClick = onMovieClick)
            }
        }
    }
}

@Composable
fun MovieRow(
    movies: List<MovieEntity>,
    onMovieClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyRow(state = listState) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            val index = movies.indexOf(movie)

            val startPadding =
                if (index == 0 && listState.firstVisibleItemIndex == 0) 14.dp else 0.dp
            val endPadding = 8.dp

            MovieItem(
                movie = movie,
                onMovieClick = onMovieClick,
                modifier = Modifier.padding(start = startPadding, end = endPadding)
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieEntity,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
        contentDescription = movie.title,
        modifier = modifier
            .width(120.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onMovieClick(movie.id) }
    )
}