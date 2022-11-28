package com.code.jamie.noteme.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Note(
    val _id: String,
    val createdAt: Long,
    val note: String,
    val owner: String,
    val title: String
)
@Entity(tableName = "note")
data class NoteDB(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val _id: String,
    val createdAt: Long,
    val note: String,
    val owner: String,
    val title: String
)
