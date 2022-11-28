package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.*
import com.code.jamie.noteme.utils.SharedPrefsUtil
import com.code.jamie.noteme.vo.models.LoginObj
import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.http.*

interface NoteMeService {

    @POST("/register")
    fun register(@Body user: RegisterModel): Call<ResponseWrapper>

    @POST("/login")
    fun login(@Body loginModel: LoginModel): Call<LoginWrapper>

    @GET("/users/{email}")
    @Headers(
        value = [
            "Accepts:application/json"
        ]
    )
    fun getUser(
        @Header(value = "access-token") token: String,
        @Path(value = "email") email: String
    ): Call<UserResponse>

    @GET("/reset/password/{email}")
    @Headers(
        value = [
            "Accepts:application/json"
        ]
    )
    fun resetPassword(email: String): Call<ResponseWrapper>

    @PUT("/users/update")
    @Headers(
        value = [
            "Content-Type:application/json"
        ]
    )
    fun updateProfile(
        @Header(value = "access-token") token: String,
        @Body user: RegisterModel
    ): Call<ResponseWrapper>

    @DELETE("/users/delete/{email}")
    @Headers(
        value = [
            "Accept:application/json"
        ]
    )
    fun deleteAccount(
        @Header(value = "access-token") token: String,
        @Path(value = "email") email: RegisterModel
    ): Call<ResponseWrapper>

    @DELETE("/logout/{email}")
    @Headers(
        value = [
            "Accept:application/json"
        ]
    )
    fun logout(
        @Header("access-token") token: String,
        @Path("email") email: String
    ): Call<ResponseWrapper>

    @POST("/notes/save")
    @Headers(
        value = [
            "Content-Type:application/json"
        ]
    )
    fun createNote(@Header("access-token") token: String, @Body note: Note): Call<ResponseWrapper>

    @GET("/notes/{email}")
    @Headers(
        value = [
            "Content-Type:application/json"
        ]
    )
    fun getMyNotes(
        @Header("access-token") token: String,
        @Path("email") email: String
    ): Call<NotesResponse>

    @PUT("/notes/update")
    @Headers(
        value = [
            "Content-Type:application/json"
        ]
    )
    fun updateNote(@Header("access-token") token: String, @Body note: Note): Call<ResponseWrapper>

    @DELETE("/notes/delete")
    @Headers(
        value = [
            "Content-Type:application/json"
        ]
    )
    fun deleteNote(@Header("access-token") token: String, @Body note: Note): Call<ResponseWrapper>


}