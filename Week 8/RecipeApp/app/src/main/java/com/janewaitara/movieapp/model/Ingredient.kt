package com.janewaitara.movieapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


class Ingredient (val image: String,
                  val originalString: String) : Parcelable {
    constructor(parcel: Parcel) : this( parcel.readString()!!,parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
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