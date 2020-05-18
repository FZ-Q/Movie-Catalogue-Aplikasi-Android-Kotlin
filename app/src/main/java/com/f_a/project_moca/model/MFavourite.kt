package com.f_a.project_moca.model

//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//

class MFavourite{
    companion object Factory {
        fun create(): MFavourite = MFavourite()
    }

    var id: String? = null
    var title: String? = null
    var genre: String? = null
    var release: String? = null
    var rating: String? = null
    var deskripsi: String? = null
    var poster: String? = null
}

//@Entity(
//    tableName = "tbl_favorite"
//)
//data class Favourite (
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    val id: Int = 0,
//
//    @ColumnInfo(name = "title")
//    val title: String,
//
//    @ColumnInfo(name = "genre")
//    val genre: String,
//
//    @ColumnInfo(name = "rate")
//    val rate: String,
//
//    @ColumnInfo(name = "release")
//    val release: String,
//
//    @ColumnInfo(name = "deskripsi")
//    val deskripsi: String,
//
//    @ColumnInfo(name = "poster")
//    val poster: String,
//
//    @ColumnInfo(name = "background")
//    val background: String
//)