package com.code.jamie.noteme.models.vo

import com.google.gson.annotations.SerializedName

data class NoteRequestWrapper(
    @SerializedName(value = "created") val created: Long,
    @SerializedName(value = "note") val note: String,
    @SerializedName(value = "owner") val owner: String,
    @SerializedName(value = "title") val title: String
)