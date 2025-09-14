package com.pocketcinema.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketcinema.data.local.MovieEntity
import com.pocketcinema.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _nowPlaying = MutableStateFlow<List<MovieEntity>>(emptyList())
    val nowPlaying: StateFlow<List<MovieEntity>> = _nowPlaying

    private val _topRated = MutableStateFlow<List<MovieEntity>>(emptyList())
    val topRated: StateFlow<List<MovieEntity>> = _topRated

    private val _searchResults = MutableStateFlow<List<MovieEntity>>(emptyList())
    val searchResults: StateFlow<List<MovieEntity>> = _searchResults

    init {
        loadNowPlaying()
        loadTopRated()
    }

    private fun loadNowPlaying() {
        viewModelScope.launch {
            repository.getNowPlayingMovies().collectLatest { movies ->
                _nowPlaying.value = movies
            }
        }
    }

    private fun loadTopRated() {
        viewModelScope.launch {
            repository.getTopRatedMovies().collectLatest { movies ->
                _topRated.value = movies
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _searchResults.value = emptyList()
            } else {
                repository.searchMovies(query).collectLatest { movies ->
                    _searchResults.value = movies
                }
            }
        }
    }
}