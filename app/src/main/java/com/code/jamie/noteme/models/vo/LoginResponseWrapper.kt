package com.code.jamie.noteme.models.vo

import com.google.gson.annotations.SerializedName

data class LoginResponseWrapper(
    @SerializedName(value = "access-token") val accessToken: String,
    @SerializedName(value = "message") val message: String
)