package com.janewaitara.movieapp

import androidx.room.*

@Dao
interface MovieDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table ORDER BY releaseDate ASC")
    fun getMovies(): List<Movie>

    @Delete
    fun deleteMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

}


@Database(entities = [Movie::class],version = 1)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
}
