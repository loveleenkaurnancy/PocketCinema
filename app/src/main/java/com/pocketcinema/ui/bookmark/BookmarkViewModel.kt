package com.pocketcinema.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocketcinema.data.local.MovieEntity
import com.pocketcinema.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {

    val bookmarkedMovies: StateFlow<List<MovieEntity>> =
        repository.getBookmarkedMovies()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}