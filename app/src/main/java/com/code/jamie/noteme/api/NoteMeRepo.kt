package com.code.jamie.noteme.api

import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import retrofit2.Response

interface NoteMeRepo {
    suspend fun register(user: User):Call<String>
    suspend fun logout(email: String):Call<String>
    suspend fun login(email:String,password:String):Call<String>
    suspend fun getAllNotes(email: String):Call<String>
    suspend fun createNote(note:String):Call<String>
    suspend fun  updateNote(note: String):Call<String>
    suspend fun deleteNote(note:String):Call<String>
    suspend fun updateProfile(user: User):Call<String>
    suspend fun deleteAccount(user: User):Call<String>
}