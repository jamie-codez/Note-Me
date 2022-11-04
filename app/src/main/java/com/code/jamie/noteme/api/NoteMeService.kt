package com.code.jamie.noteme.api

import com.code.jamie.noteme.vo.models.LoginObj
import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteMeService {

    @POST("/register")
    fun register(@Body user: User):Call<String>
    @POST("/login")
    fun login(@Body loginObj: LoginObj):Call<String>
}