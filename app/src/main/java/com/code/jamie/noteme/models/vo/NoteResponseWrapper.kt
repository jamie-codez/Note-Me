package com.code.jamie.noteme.models.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class NoteResponseWrapper(
    @SerializedName(value = "notes") val notes: List<Note>
)

data class Note(
    @SerializedName(value = "_id") val id: String,
    @SerializedName(value = "createdAt") val createdAt: Long,
    @SerializedName(value = "note") val note: String,
    @SerializedName(value = "owner") val owner: String,
    @SerializedName(value = "title") val title: String
)

@Entity(tableName = "note")
data class NoteDB(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "createdAt") val createdAt: Long,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "title") val title: String
)