package com.janewaitara.movieapp.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.networking.RemoteApi
import com.janewaitara.movieapp.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeViewModel(private val repository: RoomRepository,
                      private val remoteApi: RemoteApi
): ViewModel() {

    init {

        Log.d("Entering ViewModel", "Entering Init")

        viewModelScope.launch {

            repository.getRecipesAndInsertIntoTheDatabase()

            Log.d("Data ViewModel ", repository.getRecipesFromApi().toString())
        }
        Log.d("Data Test", repository.getAllRecipes().value.toString())
    }


    /**Using LiveData has several benefits:
    We can put an observer on the data (instead of polling for changes) and only update the
    the UI when the data actually changes.
    Repository is completely separated from the UI through the ViewModel.
     */

    /**LiveData member variable to cache the list of recipes*/
    val allRecipes: LiveData<List<Recipe>> = repository.getAllRecipes()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    fun insertRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllRecipes()
        }
    }
    /**Will be used to send generated creatures to the view layer
     *
     * Help to communicate back when the save of a creature is complete
     * Save liveData property to handle communication*/
    private val searchedRecipeLiveData = MutableLiveData<List<SearchRecipe>>()

    fun getSearchedRecipeLiveData(): LiveData<List<SearchRecipe>> = searchedRecipeLiveData

    suspend fun searchRecipeFromApiUsingSearchParameter(searchParameters: String){
        val searchedRecipes = remoteApi.searchRecipe(searchParameters)
        if (searchedRecipes is Success) {
            searchedRecipeLiveData.postValue(searchedRecipes.data)
        }
    }


}