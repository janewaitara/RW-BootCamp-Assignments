package com.janewaitara.movieapp.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.db.RecipeDao
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.ui.recipes.RecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomRepository {
    /**
     * The DAO is initialized  as opposed to the whole database.
     * This is because it only needs access to the DAO,
     * since the DAO contains all the read/write methods for the database.
     * There's no need to expose the entire database to the repository.*/

    private val recipeDao: RecipeDao = RecipeDatabase.getDatabase().recipeDao()
    private val remoteApi = RecipeApplication.remoteApi

    /**
     * The suspend modifier tells the compiler that this needs to be
     * called from a coroutine or another suspending function.
     */
    suspend fun addAllRecipes() =
        CoroutineScope(Dispatchers.Main).launch {
            val result = remoteApi.getRecipes()

            if (result is Success){
                recipeDao.insertAllRecipes(result.data)
            }else{
                Toast.makeText(RecipeApplication.getAppContext(), "Failed to fetch tasks!", Toast.LENGTH_SHORT).show()
            }

        }

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    fun getAllRecipes(): LiveData<List<Recipe>> = recipeDao.getAllRecipes()




}