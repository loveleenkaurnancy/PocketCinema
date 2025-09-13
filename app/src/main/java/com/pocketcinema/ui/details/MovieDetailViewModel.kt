package com.pocketcinema.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketcinema.data.local.MovieEntity
import com.pocketcinema.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow<MovieEntity?>(null)
    val movie: StateFlow<MovieEntity?> = _movie

    fun loadMovie(id: Int) {
        viewModelScope.launch {
            _movie.value = repository.getMovieDetails(id)
        }
    }

    fun toggleBookmark(id: Int, bookmarked: Boolean) {
        viewModelScope.launch {
            repository.setBookmark(id, bookmarked)
            _movie.value = _movie.value?.copy(isBookmarked = bookmarked)
        }
    }
}