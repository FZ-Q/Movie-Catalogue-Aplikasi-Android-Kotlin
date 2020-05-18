package com.f_a.project_moca.model

import android.os.Parcel
import android.os.Parcelable

class MMovie{
//    (
//    var id: String?,
//    var title: String?,
//    var genre: String?,
//    var release: String?,
//    var rating: String?,
//    var deskripsi: String?,
//    var poster: String?,
//    var background: String?
//
//) : Parcelable {

    companion object Factory {
        fun create(): MMovie = MMovie()
    }

    var id: String? = null
    var title: String? = null
    var genre: String? = null
    var release: String? = null
    var rating: String? = null
    var deskripsi: String? = null
    var poster: String? = null

//    constructor(parcel: Parcel) : this(
//        id = parcel.readString(),
//        title = parcel.readString(),
//        genre = parcel.readString(),
//        release = parcel.readString(),
//        rating = parcel.readString(),
//        deskripsi = parcel.readString(),
//        poster = parcel.readString(),
//        background = parcel.readString()) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(id)
//        parcel.writeString(title)
//        parcel.writeString(genre)
//        parcel.writeString(release)
//        parcel.writeString(rating)
//        parcel.writeString(deskripsi)
//        parcel.writeString(poster)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<MMovie> {
//        override fun createFromParcel(parcel: Parcel): MMovie {
//            return MMovie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<MMovie?> {
//            return arrayOfNulls(size)
//        }
//    }
}