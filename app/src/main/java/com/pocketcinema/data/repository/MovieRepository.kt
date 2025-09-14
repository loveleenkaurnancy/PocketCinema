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
        try {
            val response = api.getNowPlaying()
            val entities = response.results.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    overview = dto.overview,
                    posterPath = dto.poster_path,
                    backdropPath = dto.backdrop_path,
                    releaseDate = dto.release_date,
                    voteAverage = dto.vote_average,
                    category = "now_playing"
                )
            }
            dao.insertMovies(entities)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        emit(dao.getMoviesByCategory("now_playing").first())
    }

    fun getTopRatedMovies(): Flow<List<MovieEntity>> = flow {
        try {
            val response = api.getTopRated()
            val entities = response.results.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    overview = dto.overview,
                    posterPath = dto.poster_path,
                    backdropPath = dto.backdrop_path,
                    releaseDate = dto.release_date,
                    voteAverage = dto.vote_average,
                    category = "top_rated"
                )
            }
            dao.insertMovies(entities)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        emit(dao.getMoviesByCategory("top_rated").first())
    }

    fun getBookmarkedMovies(): Flow<List<MovieEntity>> =
        dao.getBookmarkedMovies()

    suspend fun setBookmark(movieId: Int, bookmarked: Boolean) {
        dao.updateBookmark(movieId, bookmarked)
    }

    suspend fun getMovieDetails(id: Int): MovieEntity? {
        val local = dao.getMovieById(id)
        if (local != null) return local

        return try {
            val dto = api.getMovieDetails(id)
            val entity = MovieEntity(
                id = dto.id,
                title = dto.title,
                overview = dto.overview,
                posterPath = dto.poster_path,
                backdropPath = dto.backdrop_path,
                releaseDate = dto.release_date,
                voteAverage = dto.vote_average,
                category = "details"
            )
            dao.insertMovie(entity)
            entity
        } catch (e: Exception) {
            println("Network error: ${e.message}")
            null
        }
    }

    fun searchMovies(query: String): Flow<List<MovieEntity>> = flow {
        if (query.isBlank()) {
            emit(emptyList())
            return@flow
        }

        try {
            val response = api.searchMovies(
                query = query
            )

            val entities = response.results.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    overview = dto.overview,
                    posterPath = dto.poster_path,
                    backdropPath = dto.backdrop_path,
                    releaseDate = dto.release_date,
                    voteAverage = dto.vote_average,
                    category = "search"
                )
            }

            emit(entities)

        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }
}