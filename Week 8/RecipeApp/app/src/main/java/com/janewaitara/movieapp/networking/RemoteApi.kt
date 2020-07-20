package com.janewaitara.movieapp.networking

import android.util.Log
import com.janewaitara.movieapp.BuildConfig
import com.janewaitara.movieapp.model.*
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.model.response.SearchRecipeInformationResponse
import com.janewaitara.movieapp.model.response.SearchRecipeResponse

const val BASE_URL = "https://api.spoonacular.com/"

class RemoteApi (private val remoteApiService: RemoteApiService){

    private val apiKey = BuildConfig.API_KEY
    private val numberOfRecipes = 10

    suspend fun getRecipes(): Result<List<Recipe>> = try {
        Log.d("DATA", "data.recipes[1].instructions")

        val data = remoteApiService.getRecipes(apiKey, numberOfRecipes)

        Log.d("DATA", "${data.recipes[1].image} \n ${data.recipes[1].extendedIngredients[1].image}" )
        Success(data.recipes)
    }catch (error: Throwable){
        Failure(error)
    }


    suspend fun searchRecipe(searchParameter: String ): Result<List<SearchRecipe>> = try {

        val data = remoteApiService.searchRecipes(apiKey, searchParameter )

        Success(data.results)

    }catch (error : Throwable){
        Failure(error)
    }

    suspend fun searchRecipeInformation(recipeId: Int): Result<SearchRecipeInformationResponse> = try {
        val data = remoteApiService.searchRecipeInformation(recipeId, apiKey)

        Success(data)

    }catch (error: Throwable){
        Failure(error)
    }
}
