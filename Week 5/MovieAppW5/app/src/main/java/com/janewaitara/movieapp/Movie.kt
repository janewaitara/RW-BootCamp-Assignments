package com.janewaitara.movieapp

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    var id: String,
    var title: String,
    var summary: String,
    var releaseDate: String,
    var image: Int):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(summary)
        dest.writeString(releaseDate)
        dest.writeInt(image)
    }
    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}

