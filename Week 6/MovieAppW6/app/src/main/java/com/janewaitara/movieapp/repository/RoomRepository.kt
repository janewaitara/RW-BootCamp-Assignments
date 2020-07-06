package com.janewaitara.movieapp.repository

import androidx.lifecycle.LiveData
import com.janewaitara.movieapp.db.MovieDao
import com.janewaitara.movieapp.db.MovieDatabase
import com.janewaitara.movieapp.model.Movie

class RoomRepository {
    /**
     * The DAO is initialized  as opposed to the whole database.
     * This is because it only needs access to the DAO,
     * since the DAO contains all the read/write methods for the database.
     * There's no need to expose the entire database to the repository.*/

    private val movieDao: MovieDao = MovieDatabase.getDatabase().movieDao()

    /**
     * The suspend modifier tells the compiler that this needs to be
     * called from a coroutine or another suspending function.
     */
    suspend fun addMovie(movie: Movie) = movieDao.insertMovie(movie)

    suspend fun addAllMovies(movieList: List<Movie>) = movieDao.insertAllMovies(movieList)

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    fun getAllMovies(): LiveData<List<Movie>> = movieDao.getAllMovies()

    fun getMovie(id: Int): LiveData<Movie> = movieDao.getMovie(id)

    fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)


}