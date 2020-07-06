package com.janewaitara.movieapp

import android.content.Context
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

@Database(entities = [Movie::class],version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object{

        /**Singleton prevents multiple instances of
         *  database opening at the same time.*/
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        /**
         * This function returns the singleton. It'll create the database the
         *  first time it's accessed, using Room's database
         *  builder to create a RoomDatabase object in the
         *  application context from the WordRoomDatabase class
         *  and names it "movie_database".
         * */
        fun getDatabase(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
