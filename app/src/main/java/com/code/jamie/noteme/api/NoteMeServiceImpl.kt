package com.code.jamie.noteme.api

import com.code.jamie.noteme.vo.models.LoginObj
import com.code.jamie.noteme.vo.models.User
import retrofit2.Call
import javax.inject.Inject

class NoteMeServiceImpl @Inject constructor(private val noteMeService: NoteMeService) :NoteMeRepo {
    override suspend fun register(user: User): Call<String> {
        return noteMeService.register(user)
    }

    override suspend fun logout(email: String): Call<String> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Call<String> {
        return noteMeService.login(LoginObj(email,password))
    }

    override suspend fun getAllNotes(email: String): Call<String> {
        return noteMeService.getMyNotes(email)
    }

    override suspend fun createNote(note: String): Call<String> {
        return noteMeService.createNote(note)
    }

    override suspend fun updateNote(note: String): Call<String> {
        return noteMeService.updateNote(note)
    }

    override suspend fun deleteNote(note: String): Call<String> {
        return noteMeService.deleteNote(note)
    }

    override suspend fun updateProfile(user: User): Call<String> {
        return noteMeService.updateProfile(user)
    }

    override suspend fun deleteAccount(user: User): Call<String> {
        return noteMeService.deleteAccount(user)
    }
}