package com.janewaitara.movieapp.model.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class SearchRecipeResponse(
    val results: List<SearchRecipe> = mutableListOf())

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchRecipe(
    val id: Int,
    val title: String,
    val image: String
):Parcelable

