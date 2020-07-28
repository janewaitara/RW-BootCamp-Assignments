package com.janewaitara.movieapp.ui.recipes.searchRecipeInformation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.model.response.SearchRecipeInformationResponse
import com.janewaitara.movieapp.networking.RemoteApi

class SearchRecipeInformationViewModel(private val remoteApi: RemoteApi): ViewModel() {
    /**Will be used to send generated creatures to the view layer
     *
     * Help to communicate back when the save of a creature is complete
     * Save liveData property to handle communication*/
    private val searchedRecipeInfoLiveData = MutableLiveData<SearchRecipeInformationResponse>()

    fun getSearchedInfoRecipeLiveData(): LiveData<SearchRecipeInformationResponse> = searchedRecipeInfoLiveData

    suspend fun searchRecipeFromApiUsingSearchParameter(recipeId: Int){
        val searchedRecipesInfo = remoteApi.searchRecipeInformation(recipeId)

        Log.d("Recipe Information", searchedRecipesInfo.toString())

        if (searchedRecipesInfo is Success) {
            searchedRecipeInfoLiveData.postValue(searchedRecipesInfo.data)
        }
    }

}