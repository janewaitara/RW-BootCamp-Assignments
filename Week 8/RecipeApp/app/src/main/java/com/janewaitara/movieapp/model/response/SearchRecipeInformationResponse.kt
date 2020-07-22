package com.janewaitara.movieapp.model.response

import com.janewaitara.movieapp.model.Ingredient
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRecipeInformationResponse (
    val id: Int,
    val title: String,
    val summary: String,
    val readyInMinutes: Int,
    val image: String,
    val instructions: String?,
    val extendedIngredients: List<Ingredient>

)

class SearchRecipeIngredient (val image: String,
                              val originalString: String)