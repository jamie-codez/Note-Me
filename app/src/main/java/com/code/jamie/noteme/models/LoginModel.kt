package com.code.jamie.noteme.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
)
