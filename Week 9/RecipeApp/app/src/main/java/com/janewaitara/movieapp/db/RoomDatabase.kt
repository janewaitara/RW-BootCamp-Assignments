package com.janewaitara.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.model.IngredientsConverter
import com.janewaitara.movieapp.model.Recipe


@Dao
interface RecipeDao{
    /**
    *Room has coroutines support, allowing your queries to be
     *annotated with the suspend modifier and then called
     *from a coroutine or from another suspension function.
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllRecipes(recipes: List<Recipe>)

    @Query("SELECT * FROM recipe_table where id = :movieId")
    fun getRecipe(movieId: Int): LiveData<Recipe>

    @Query("SELECT * FROM recipe_table ORDER BY id ASC")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)

}

@Database(entities = [Recipe::class],version = 1, exportSchema = false)
@TypeConverters(IngredientsConverter::class)
abstract class RecipeDatabase: RoomDatabase(){

    abstract fun recipeDao(): RecipeDao

    companion object{

        /**Singleton prevents multiple instances of
         *  database opening at the same time.*/
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        val context =
            RecipeApplication.getAppContext()

        /**
         * This function returns the singleton. It'll create the database the
         *  first time it's accessed, using Room's database
         *  builder to create a RoomDatabase object in the
         *  application context from the WordRoomDatabase class
         *  and names it "movie_database".
         * */
        fun getDatabase(): RecipeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "movie_database"
                )/*.addCallback(
                    RecipeDatabaseCallback(
                        CoroutineScope(Dispatchers.IO)
                    )
                )*/.build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
 /*   private class RecipeDatabaseCallback(private val scope: CoroutineScope)
        : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            scope.launch {
                var recipeDao = getDatabase()
                    .recipeDao()
                //populating
                recipeDao.insertAllRecipes(RecipeViewModel.recipeList)
            }
        }

    }*/

}
