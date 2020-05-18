//package com.f_a.project_moca.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.f_a.project_moca.model.Favourite
//
//@Dao
//interface DaoFavourite {
//    @Query("SELECT * FROM tbl_favorite")
//    fun getAllFavorite(): List<Favourite>
//
//    @Query("SELECT * FROM tbl_favorite WHERE id = :idM")
//    fun findOneById(idM: Int?): Favourite?
//
//    @Query("SELECT DISTINCT * FROM tbl_favorite WHERE title = :name")
//    fun findByName(name: String?): Favourite?
//
//    @Query("DELETE FROM tbl_favorite WHERE title= :name")
//    fun deleteByName(name: String?)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(favourite: Favourite)
//
////    @Delete
////    fun delete(favourite: Favourite)
//
//}