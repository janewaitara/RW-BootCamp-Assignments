package com.janewaitara.movieapp.model

class Recipe(
    val id : Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val summary: String,
    val instructions: String,
    val extendedIngredients: Array<Ingredients>
)

class Ingredients ( val originalString: String)