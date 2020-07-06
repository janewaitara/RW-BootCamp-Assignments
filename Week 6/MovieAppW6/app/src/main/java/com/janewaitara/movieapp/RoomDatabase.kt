package com.janewaitara.movieapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao{
    /**
    *Room has coroutines support, allowing your queries to be
     *annotated with the suspend modifier and then called
     *from a coroutine or from another suspension function.
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getMovie(movieId: Int): LiveData<Movie>

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

}

@Database(entities = [Movie::class],version = 1)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
}
