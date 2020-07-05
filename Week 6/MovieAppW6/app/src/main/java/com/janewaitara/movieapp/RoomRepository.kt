package com.janewaitara.movieapp

class RoomRepository {

    private val movieDao: MovieDao = MovieApplication.database.movieDao()

    fun addMovie(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllMovies(): List<Movie> = movieDao.getMovies()

    fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)

    fun deleteMovie(movie: Movie) = movieDao.deleteMovie(movie)


}