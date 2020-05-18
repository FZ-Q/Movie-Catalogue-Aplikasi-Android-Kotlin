//package com.f_a.project_moca.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.f_a.project_moca.model.Favourite
//
//@Database(
//    entities = [Favourite::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class AppDatabase: RoomDatabase() {
//    abstract fun favouriteDao(): DaoFavourite
//
//    companion object {
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                        AppDatabase::class.java, "MoCa.db")
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//    }
//}