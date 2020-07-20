package com.janewaitara.movieapp.networking

import android.util.Log
import com.janewaitara.movieapp.BuildConfig
import com.janewaitara.movieapp.model.*

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

}
