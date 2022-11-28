package com.code.jamie.noteme.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(value = "user") val user: User
)