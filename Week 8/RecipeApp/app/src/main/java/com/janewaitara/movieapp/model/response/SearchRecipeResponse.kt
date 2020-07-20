package com.janewaitara.movieapp.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRecipeResponse(
    val results: List<SearchRecipe> = mutableListOf())

@JsonClass(generateAdapter = true)
data class SearchRecipe(
    val id: Int,
    val title: String,
    val image: String,
    val nutrition: List<Nutrition>
)
class Nutrition (val title: String,
                 val amount: Int,
                 val unit: String)