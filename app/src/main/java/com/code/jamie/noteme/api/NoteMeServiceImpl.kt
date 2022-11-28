package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.*
import retrofit2.Call
import javax.inject.Inject

class NoteMeServiceImpl @Inject constructor(private val noteMeService: NoteMeService) :NoteMeRepo {
    override suspend fun register(user: RegisterModel): Call<ResponseWrapper> {
        return noteMeService.register(user)
    }

    override suspend fun logout(token: String,email: String): Call<ResponseWrapper> {
        return noteMeService.logout(token,email)
    }

    override suspend fun getUser(token: String,email: String): Call<UserResponse> {
        return noteMeService.getUser(token,email)
    }

    override suspend fun login(email: String, password: String): Call<LoginWrapper> {
        return noteMeService.login(LoginModel(email, password))
    }

    override suspend fun getAllNotes(token: String,email: String): Call<NotesResponse> {
        return noteMeService.getMyNotes(token,email)
    }

    override suspend fun createNote(token: String,note: Note): Call<ResponseWrapper> {
        return noteMeService.createNote(token,note)
    }

    override suspend fun updateNote(token: String,note: Note): Call<ResponseWrapper> {
        return noteMeService.updateNote(token,note)
    }

    override suspend fun deleteNote(token: String,note: Note): Call<ResponseWrapper> {
        return noteMeService.deleteNote(token,note)
    }

    override suspend fun updateProfile(token: String,user: RegisterModel): Call<ResponseWrapper> {
        return noteMeService.updateProfile(token,user)
    }

    override suspend fun deleteAccount(token: String,user: RegisterModel): Call<ResponseWrapper> {
        return noteMeService.deleteAccount(token,user)
    }
}