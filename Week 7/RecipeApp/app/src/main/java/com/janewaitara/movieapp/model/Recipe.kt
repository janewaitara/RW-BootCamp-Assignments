package com.janewaitara.movieapp.model

import kotlinx.serialization.Serializable

@Serializable
class Recipe(
    val id : Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val summary: String,
    val instructions: String,
    val extendedIngredients: Array<Ingredients>
)

@Serializable
class Ingredients ( val originalString: String)