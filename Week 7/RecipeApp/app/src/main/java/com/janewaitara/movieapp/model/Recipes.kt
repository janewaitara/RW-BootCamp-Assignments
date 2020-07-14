package com.janewaitara.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
class Recipes(
    val id : Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val summary: String,
    val instructions: String,
    val extendedIngredients: Array<Ingredient>
)

@Serializable
class Ingredient (var originalString: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalString)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}