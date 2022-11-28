package com.code.jamie.noteme.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "_id") val id: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "image-url") val imageUrl: Any,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "username") val username: String,
    @SerializedName(value = "verified") val verified: Boolean
)