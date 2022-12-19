package com.code.jamie.noteme.models.vo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NoteResponseWrapper(
    @SerializedName(value = "notes") val notes: List<Note>
)

@Parcelize
data class Note(
    @SerializedName(value = "_id") val id: String,
    @SerializedName(value = "createdAt") val createdAt: Long,
    @SerializedName(value = "note") val note: String,
    @SerializedName(value = "owner") val owner: String,
    @SerializedName(value = "title") val title: String
):Parcelable

@Entity(tableName = "note")
@Parcelize
data class NoteDB(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "id_obj") val _id: String,
    @ColumnInfo(name = "createdAt") val createdAt: Long,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "title") val title: String
):Parcelable