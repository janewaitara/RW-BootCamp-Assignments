package com.janewaitara.movieapp.model

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class GetRecipesResponse(
    val recipes: List<Recipe> = mutableListOf())


