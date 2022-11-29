package com.code.jamie.noteme.models.vo

import com.google.gson.annotations.SerializedName

data class RegisterRequestWrapper(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "image-url") val imageUrl: String,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "username") val username: String
)