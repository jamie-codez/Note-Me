package com.code.jamie.noteme.ui.viewmodels

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.code.jamie.noteme.api.NoteMeRepo
import com.code.jamie.noteme.models.vo.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val noteMeRepo: NoteMeRepo,
    private val roomViewModel: RoomViewModel,
    application: Application
) :
    AndroidViewModel(application) {
    var registerMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var loginMessage: MutableLiveData<LoginResponseWrapper?> = MutableLiveData()
    var updateUserMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var deleteUserMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var logoutUserMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var resetPassUserMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var createNoteMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var updateNoteMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var getNotesNotesBody: MutableLiveData<NoteResponseWrapper?> = MutableLiveData()
    var deleteNoteMessage: MutableLiveData<MessageResponseWrapper?> = MutableLiveData()
    var user: MutableLiveData<UserResponseWrapper?> = MutableLiveData()

    fun register(register: RegisterRequestWrapper): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            val reg = noteMeRepo.register(register)
            reg.enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 201) {
                        registerMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    registerMessage.postValue(null)
                }
            })
        }
        return registerMessage
    }

    fun login(login: LoginRequestWrapper): MutableLiveData<LoginResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.login(login).enqueue(object : Callback<LoginResponseWrapper> {
                override fun onResponse(
                    call: Call<LoginResponseWrapper>,
                    response: Response<LoginResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        loginMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<LoginResponseWrapper>, t: Throwable) {
                    loginMessage.postValue(null)
                }
            })
        }
        return loginMessage
    }

    fun getUser(token: String, email: String): MutableLiveData<UserResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.getUser(token, email).enqueue(object : Callback<UserResponseWrapper> {
                override fun onResponse(
                    call: Call<UserResponseWrapper>,
                    response: Response<UserResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        user.postValue(response.body())
                        roomViewModel.saveUser(
                            UserDB(
                                0,
                                response.body()?.user!!.id,
                                response.body()?.user!!.email,
                                response.body()?.user!!.imageUrl,
                                response.body()?.user!!.password,
                                response.body()?.user!!.username,
                                response.body()?.user!!.verified
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<UserResponseWrapper>, t: Throwable) {
                    user.postValue(null)
                }

            })
        }
        return user
    }

    fun updateUser(
        token: String,
        email: String,
        user: User
    ): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.updateUser(token, email, user)
                .enqueue(object : Callback<MessageResponseWrapper> {
                    override fun onResponse(
                        call: Call<MessageResponseWrapper>,
                        response: Response<MessageResponseWrapper>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            updateUserMessage.postValue(response.body())
                            roomViewModel.updateUserDB(
                                UserDB(
                                    0,
                                    user.id,
                                    user.username,
                                    user.email,
                                    user.imageUrl,
                                    user.password,
                                    user.verified
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                        updateUserMessage.postValue(null)
                    }
                })
        }
        return updateUserMessage
    }

    fun deleteUser(token: String, email: String): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.deleteUser(token, email).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        deleteUserMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    deleteUserMessage.postValue(null)
                }
            })
        }
        return deleteUserMessage
    }

    fun logout(email: String): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.logout(email).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        logoutUserMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    logoutUserMessage.postValue(null)
                }
            })
        }
        return logoutUserMessage
    }

    fun resetPassword(email: String): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.resetPassword(email).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        resetPassUserMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    resetPassUserMessage.postValue(null)
                }
            })
        }
        return resetPassUserMessage
    }

    fun createNote(
        token: String,
        note: NoteRequestWrapper
    ): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.createNote(token, note).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200 || response.code() == 210) {
                        createNoteMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    createNoteMessage.postValue(null)
                }
            })
        }
        return createNoteMessage
    }

    fun getNotes(token: String): MutableLiveData<NoteResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.getNotes(token).enqueue(object : Callback<NoteResponseWrapper> {
                override fun onResponse(
                    call: Call<NoteResponseWrapper>,
                    response: Response<NoteResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        getNotesNotesBody.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<NoteResponseWrapper>, t: Throwable) {
                    getNotesNotesBody.postValue(null)
                }
            })
        }
        return getNotesNotesBody
    }

    fun updateNote(
        token: String,
        note: NoteOpRequestWrapper
    ): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.updateNote(token, note).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200 || response.code() == 210) {
                        updateNoteMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    updateNoteMessage.postValue(null)
                }
            })
        }
        return updateNoteMessage
    }

    fun deleteNote(
        token: String,
        note: NoteOpRequestWrapper
    ): MutableLiveData<MessageResponseWrapper?> {
        viewModelScope.launch {
            noteMeRepo.deleteNote(token, note).enqueue(object : Callback<MessageResponseWrapper> {
                override fun onResponse(
                    call: Call<MessageResponseWrapper>,
                    response: Response<MessageResponseWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200 || response.code() == 210) {
                        deleteNoteMessage.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MessageResponseWrapper>, t: Throwable) {
                    deleteNoteMessage.postValue(null)
                }
            })
        }
        return deleteNoteMessage
    }

}