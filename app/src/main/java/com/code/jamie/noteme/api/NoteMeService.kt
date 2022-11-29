package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.vo.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NoteMeService {
    /**
     * User methods
     */
    @POST("/register")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun register(@Body register: RegisterRequestWrapper): Call<MessageResponseWrapper>

    @POST("/login")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun login(@Body login: LoginRequestWrapper): Call<LoginResponseWrapper>

    @GET("/users/{email}")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun getUser(
        @Header("access-token") token: String,
        @Path(value = "email") email: String
    ): Call<UserResponseWrapper>

    @PUT("/users/update/{email}")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun updateUser(
        @Header("access-token") token: String,
        @Path(value = "email") email: String,
        user: User
    ): Call<MessageResponseWrapper>

    @DELETE("/users/delete/{email}")
    suspend fun deleteUser(
        @Header("access-token") token: String,
        @Path(value = "email") email: String
    ): Call<MessageResponseWrapper>

    @DELETE("/logout/{email}")
    suspend fun logout(
        @Path(value = "email") email: String
    ): Call<MessageResponseWrapper>

    @GET("/reset/password/{email}")
    suspend fun resetPassword(@Path(value = "email") email: String): Call<MessageResponseWrapper>

    /**
     * Notes methods
     */
    @POST("/notes/create")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun createNote(
        @Header("access-token") token: String,
        note: NoteRequestWrapper
    ): Call<MessageResponseWrapper>

    @GET("/notes")
    suspend fun getNotes(@Header("access-token") token: String): Call<NoteResponseWrapper>

    @PUT("/notes/update")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun updateNote(
        @Header("access-token") token: String,
        noteOp: NoteOpRequestWrapper
    ): Call<MessageResponseWrapper>

    @DELETE("/notes/delete")
    @Headers(value = ["Content-Type:application/json"])
    suspend fun deleteNote(
        @Header("access-token") token: String,
        noteOp: NoteOpRequestWrapper
    ): Call<MessageResponseWrapper>
}