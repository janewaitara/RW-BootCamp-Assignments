package com.janewaitara.movieapp.networking

import com.janewaitara.movieapp.model.response.GetRecipesResponse
import com.janewaitara.movieapp.model.response.SearchRecipeInformationResponse
import com.janewaitara.movieapp.model.response.SearchRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") searchParameter: String
    ): SearchRecipeResponse

    @GET("recipes/{recipeId}/information")
    suspend fun searchRecipeInformation(
        @Path("recipeId") recipeId : Int,
        @Query("apiKey") apiKey: String
    ): SearchRecipeInformationResponse
}