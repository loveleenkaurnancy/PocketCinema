package com.pocketcinema.data.repository

import com.pocketcinema.data.local.MovieDao
import com.pocketcinema.data.local.MovieEntity
import com.pocketcinema.data.remote.TmdbApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: TmdbApi,
    private val dao: MovieDao
) {
    fun getNowPlayingMovies(): Flow<List<MovieEntity>> = flow {
        // Load from network
        val response = api.getNowPlaying()
        val entities = response.results.map { dto ->
            MovieEntity(
                id = dto.id,
                title = dto.title,
                overview = dto.overview,
                posterPath = dto.poster_path,
                backdropPath = dto.backdrop_path,
                releaseDate = dto.release_date
            )
        }
        dao.insertMovies(entities)

        // Emit from DB (cached)
        emit(dao.getAllMovies().first())
    }

    fun getTopRatedMovies(): Flow<List<MovieEntity>> = flow {
        val response = api.getTopRated()
        val entities = response.results.map { dto ->
            MovieEntity(
                id = dto.id,
                title = dto.title,
                overview = dto.overview,
                posterPath = dto.poster_path,
                backdropPath = dto.backdrop_path,
                releaseDate = dto.release_date
            )
        }
        dao.insertMovies(entities)

        emit(dao.getAllMovies().first())
    }

    fun getBookmarkedMovies(): Flow<List<MovieEntity>> = dao.getBookmarkedMovies()

    suspend fun setBookmark(movieId: Int, bookmarked: Boolean) {
        dao.updateBookmark(movieId, bookmarked)
    }

    suspend fun getMovieDetails(id: Int): MovieEntity? {
        // First try DB
        val local = dao.getMovieById(id)
        if (local != null) return local

        // Else fetch from network
        val dto = api.getMovieDetails(id)
        val entity = MovieEntity(
            id = dto.id,
            title = dto.title,
            overview = dto.overview,
            posterPath = dto.poster_path,
            backdropPath = dto.backdrop_path,
            releaseDate = dto.release_date
        )
        dao.insertMovie(entity)
        return entity
    }
}