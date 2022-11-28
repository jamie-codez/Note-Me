package com.code.jamie.noteme.models

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName(value = "username") val username: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password:String,
    @SerializedName(value = "image-url") val imageUrl:String
)
