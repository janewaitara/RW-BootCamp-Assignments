package com.janewaitara.movieapp.networking

import com.janewaitara.movieapp.BuildConfig
import com.janewaitara.movieapp.model.*

const val BASE_URL = "https://api.spoonacular.com"

class RemoteApi (private val remoteApiService: RemoteApiService){

    private val apiKey = BuildConfig.API_KEY
    private val numberOfRecipes = 10

    suspend fun getRecipes(): Result<List<Recipes>> = try {

        val data = remoteApiService.getRecipes(apiKey, numberOfRecipes)

        Success(data.recipes)
    }catch (error: Throwable){
        Failure(error)
    }

}

