package com.code.jamie.noteme.models

import com.google.gson.annotations.SerializedName

data class LoginWrapper(
    @SerializedName(value = "access-token") val accessToken: String,
    val message: String
)