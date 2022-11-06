package com.code.jamie.noteme.api

import com.code.jamie.noteme.vo.models.LoginObj
import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface NoteMeService {

    @POST("/register")
    fun register(@Body user: User):Call<String>
    @POST("/login")
    fun login(@Body loginObj: LoginObj):Call<String>
    @GET("/create")
    fun createNote(@Body note:String):Call<String>
    @GET("/getNOtes")
    fun getMyNotes(@Query("email") email:String):Call<String>
    @PUT("/updatenote")
    fun updateNote(@Body note: String):Call<String>
    @DELETE("/deleteNote")
    fun deleteNote(@Body note: String):Call<String>
    @PUT("/updateProfile")
    fun updateProfile(@Body user: User):Call<String>
    @DELETE("/deleteAccount")
    fun deleteAccount(@Body user: User):Call<String>
}