package com.janewaitara.movieapp.networking

import com.janewaitara.movieapp.model.GetRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Represent the API calls you can make
 * */
interface RemoteApiService {

    @GET("recipes/random")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): GetRecipesResponse
}