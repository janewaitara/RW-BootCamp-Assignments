package com.janewaitara.movieapp.model

import kotlinx.serialization.Serializable

@Serializable
data class GetRecipesResponse(
    val recipes: List<Recipes> = mutableListOf())
