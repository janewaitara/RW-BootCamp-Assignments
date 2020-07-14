package com.janewaitara.movieapp.repository

import androidx.lifecycle.LiveData
import com.janewaitara.movieapp.db.RecipeDao
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.model.Recipe

class RoomRepository {
    /**
     * The DAO is initialized  as opposed to the whole database.
     * This is because it only needs access to the DAO,
     * since the DAO contains all the read/write methods for the database.
     * There's no need to expose the entire database to the repository.*/

    private val recipeDao: RecipeDao = RecipeDatabase.getDatabase().recipeDao()

    /**
     * The suspend modifier tells the compiler that this needs to be
     * called from a coroutine or another suspending function.
     */
    suspend fun addRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)

    suspend fun addAllRecipes(recipeList: List<Recipe>) = recipeDao.insertAllRecipes(recipeList)

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    fun getAllRecipes(): LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    fun getRecipe(id: Int): LiveData<Recipe> = recipeDao.getRecipe(id)

    fun updateRecipe(recipe: Recipe) = recipeDao.updateRecipe(recipe)

    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)


}