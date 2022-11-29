package com.code.jamie.noteme.models.vo

import com.google.gson.annotations.SerializedName

data class LoginRequestWrapper(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
)