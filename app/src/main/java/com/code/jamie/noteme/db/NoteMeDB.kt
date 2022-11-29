package com.code.jamie.noteme.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.code.jamie.noteme.models.vo.NoteDB
import com.code.jamie.noteme.models.vo.UserDB

@Database(entities = [UserDB::class, NoteDB::class], version = 1, exportSchema = false)
abstract class NoteMeDB : RoomDatabase() {
    abstract fun noteMeDao(): NoteMeDao

    companion object {
        @JvmStatic
        val db_name = "noteme.db"
        private var instance: NoteMeDB? = null

        @JvmStatic
        fun getInstance(context: Context): NoteMeDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context, NoteMeDB::class.java, db_name)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}