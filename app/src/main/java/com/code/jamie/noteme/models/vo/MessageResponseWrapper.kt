package com.code.jamie.noteme.models.vo

import com.google.gson.annotations.SerializedName

data class MessageResponseWrapper(
    @SerializedName(value = "message") val message: String
)