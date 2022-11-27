package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.AppUser
import retrofit2.Response
import retrofit2.http.Body

interface NoteMeService {
    suspend fun register(@Body user: AppUser):Response<*>
}