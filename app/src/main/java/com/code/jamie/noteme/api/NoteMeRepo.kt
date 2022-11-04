package com.code.jamie.noteme.api

import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.Response

class NoteMeRepo(private val noteMeService: NoteMeService) {
    suspend fun register(user: User):Call<String>{
        return noteMeService.register(user)
    }
}