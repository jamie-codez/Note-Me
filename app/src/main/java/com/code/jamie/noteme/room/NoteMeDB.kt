package com.code.jamie.noteme.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.code.jamie.noteme.vo.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteMeDB : RoomDatabase() {
    abstract fun noteMeDao(): NoteMeDao

    companion object {
        @JvmStatic
        val DB_NAME = "NoteMe"
    }
}