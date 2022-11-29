package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.vo.LoginRequestWrapper
import com.code.jamie.noteme.models.vo.LoginResponseWrapper
import com.code.jamie.noteme.models.vo.MessageResponseWrapper
import com.code.jamie.noteme.models.vo.RegisterRequestWrapper
import retrofit2.Call
import retrofit2.http.Body

interface NoteMeService {
    suspend fun register(@Body register:RegisterRequestWrapper):Call<MessageResponseWrapper>
    suspend fun login(@Body login:LoginRequestWrapper):Call<LoginResponseWrapper>
}