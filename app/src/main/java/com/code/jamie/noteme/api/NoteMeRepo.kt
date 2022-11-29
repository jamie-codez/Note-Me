package com.code.jamie.noteme.api

import com.code.jamie.noteme.models.vo.*
import retrofit2.Call
import javax.inject.Inject

class NoteMeRepo @Inject constructor(private val noteMeService: NoteMeService) {
    suspend fun register(register: RegisterRequestWrapper): Call<MessageResponseWrapper> =
        noteMeService.register(register)


    suspend fun login(login: LoginRequestWrapper): Call<LoginResponseWrapper> =
        noteMeService.login(login)


    suspend fun getUser(token: String, email: String): Call<UserResponseWrapper> =
        noteMeService.getUser(token, email)


    suspend fun updateUser(token: String, email: String, user: User): Call<MessageResponseWrapper> {
        return noteMeService.updateUser(token, email, user)
    }

    suspend fun deleteUser(token: String, email: String): Call<MessageResponseWrapper> =
        noteMeService.deleteUser(token, email)

    suspend fun logout(email: String): Call<MessageResponseWrapper> =
        noteMeService.logout(email)


    suspend fun resetPassword(email: String): Call<MessageResponseWrapper> =
        noteMeService.resetPassword(email)


    suspend fun createNote(token: String, note: NoteRequestWrapper): Call<MessageResponseWrapper> =
        noteMeService.createNote(token, note)


    suspend fun getNotes(token: String): Call<NoteResponseWrapper> =
        noteMeService.getNotes(token)


    suspend fun updateNote(
        token: String,
        note: NoteOpRequestWrapper
    ): Call<MessageResponseWrapper> = noteMeService.updateNote(token, note)


    suspend fun deleteNote(
        token: String,
        note: NoteOpRequestWrapper
    ): Call<MessageResponseWrapper> = noteMeService.deleteNote(token, note)

}