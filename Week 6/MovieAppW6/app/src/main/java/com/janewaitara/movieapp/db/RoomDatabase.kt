package com.janewaitara.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.janewaitara.movieapp.model.Movie
import com.janewaitara.movieapp.MovieApplication
import com.janewaitara.movieapp.ui.movies.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    @Query("SELECT * FROM movie_table where id = :movieId")
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
        val context =
            MovieApplication.getAppContext()

        /**
         * This function returns the singleton. It'll create the database the
         *  first time it's accessed, using Room's database
         *  builder to create a RoomDatabase object in the
         *  application context from the WordRoomDatabase class
         *  and names it "movie_database".
         * */
        fun getDatabase(): MovieDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie_database"
                ).addCallback(
                    MovieDatabaseCallback(
                        CoroutineScope(Dispatchers.IO)
                    )
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class MovieDatabaseCallback(private val scope: CoroutineScope)
        : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            scope.launch {
                var movieDao = getDatabase()
                    .movieDao()
                //populating
                movieDao.insertAllMovies(MovieViewModel.movieList)
            }
        }

    }

}
