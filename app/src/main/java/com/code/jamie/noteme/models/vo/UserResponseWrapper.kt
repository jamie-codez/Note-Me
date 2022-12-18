package com.code.jamie.noteme.models.vo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponseWrapper(
    @SerializedName(value = "user") val user: User
)

data class User(
    @SerializedName(value = "_id") val id: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "image-url") val imageUrl: String,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "username") val username: String,
    @SerializedName(value = "verified") val verified: Boolean
)

@Entity(tableName = "user")
@Parcelize
data class UserDB(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "id_obj") val _id: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "verified") val verified: Boolean
):Parcelable