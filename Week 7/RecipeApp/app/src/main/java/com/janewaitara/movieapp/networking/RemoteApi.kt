package com.janewaitara.movieapp.networking

import com.janewaitara.movieapp.model.*

const val BASE_URL = "https://api.spoonacular.com"

class RemoteApi (private val remoteApiService: RemoteApiService){

    private val apiKey = "f53be2edac6c4f658065e041ec47cc91"
    private val numberOfRecipes = 10

    suspend fun getRecipes(): Result<List<Recipe>> = try {

        val data = remoteApiService.getRecipes(apiKey, numberOfRecipes)

        Success(data.recipes)
    }catch (error: Throwable){
        Failure(error)
    }

}

