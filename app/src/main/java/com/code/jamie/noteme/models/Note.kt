package com.code.jamie.noteme.models

data class Note(
    val _id: String,
    val createdAt: Long,
    val note: String,
    val owner: String,
    val title: String
)