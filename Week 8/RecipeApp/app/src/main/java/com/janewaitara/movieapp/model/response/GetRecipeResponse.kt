package com.janewaitara.movieapp.model.response

import com.janewaitara.movieapp.model.Recipe
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRecipesResponse(
    val recipes: List<Recipe> = mutableListOf())


