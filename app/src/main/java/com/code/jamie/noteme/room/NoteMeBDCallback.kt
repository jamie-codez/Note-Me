package com.code.jamie.noteme.room

import android.app.Application
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.code.jamie.noteme.utils.SharedPrefsUtil

class NoteMeBDCallback(private val app:Application):RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        SharedPrefsUtil.clearNoteMePrefs(app)
        super.onCreate(db)
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        SharedPrefsUtil.clearNoteMePrefs(app)
        super.onDestructiveMigration(db)
    }
}