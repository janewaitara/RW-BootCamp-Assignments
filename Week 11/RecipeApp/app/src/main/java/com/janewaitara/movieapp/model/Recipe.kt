package com.janewaitara.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "recipe title") val title: String,
    @ColumnInfo(name = "recipe summary") val summary: String,
    val readyInMinutes: Int,
    val image: String,
    val instructions: String,
    val extendedIngredients: List<Ingredient>

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Ingredient)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(summary)
        parcel.writeInt(readyInMinutes)
        parcel.writeString(image)
        parcel.writeString(instructions)
        parcel.writeTypedList(extendedIngredients)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}



