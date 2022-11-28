package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.*
import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.Response

interface NoteMeRepo {
    suspend fun register(user: RegisterModel):Call<ResponseWrapper>
    suspend fun login(email:String,password:String):Call<LoginWrapper>
    suspend fun logout(token: String,email: String):Call<ResponseWrapper>
    suspend fun getUser(token:String,email: String):Call<UserResponse>
    suspend fun getAllNotes(token: String,email: String):Call<NotesResponse>
    suspend fun createNote(token: String,note:Note):Call<ResponseWrapper>
    suspend fun  updateNote(token: String,note: Note):Call<ResponseWrapper>
    suspend fun deleteNote(token: String,note:Note):Call<ResponseWrapper>
    suspend fun updateProfile(token: String,user: RegisterModel):Call<ResponseWrapper>
    suspend fun deleteAccount(token: String,user: RegisterModel):Call<ResponseWrapper>
}