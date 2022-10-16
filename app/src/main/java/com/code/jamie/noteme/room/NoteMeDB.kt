package com.code.jamie.noteme.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [String::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteMeDB : RoomDatabase() {
    abstract fun noteMeDao(): NoteMeDao

    companion object {
        @JvmStatic
        val DB_NAME = "NoteMe"
    }
}