package com.code.jamie.noteme.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.code.jamie.noteme.api.NoteMeRepo
import com.code.jamie.noteme.api.NoteMeService
import com.code.jamie.noteme.api.NoteMeServiceImpl
import com.code.jamie.noteme.models.LoginModel
import com.code.jamie.noteme.models.LoginWrapper
import com.code.jamie.noteme.models.RegisterModel
import com.code.jamie.noteme.models.ResponseWrapper
import com.code.jamie.noteme.vo.models.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application, noteMeService: NoteMeService) :
    AndroidViewModel(application) {

    private val registerResponse: MutableLiveData<ResponseWrapper?> = MutableLiveData()
    private val loginResponse: MutableLiveData<LoginWrapper?> = MutableLiveData()
    private val noteMeServiceImpl: NoteMeServiceImpl = NoteMeServiceImpl(noteMeService)
    fun register(user: RegisterModel): MutableLiveData<ResponseWrapper?> {
        viewModelScope.launch {
            val call = noteMeServiceImpl.register(user)
            call.enqueue(object : Callback<ResponseWrapper> {
                override fun onResponse(
                    call: Call<ResponseWrapper>,
                    response: Response<ResponseWrapper>
                ) {
                    if (response.isSuccessful) {
                        registerResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ResponseWrapper>, t: Throwable) {
                    registerResponse.postValue(null)
                }

            })
        }
        return registerResponse
    }

    fun login(email: String, password: String): MutableLiveData<LoginWrapper?> {
        viewModelScope.launch {
            val call = noteMeServiceImpl.login(email, password)
            call.enqueue(object : Callback<LoginWrapper> {
                override fun onResponse(
                    call: Call<LoginWrapper>,
                    response: Response<LoginWrapper>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        loginResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<LoginWrapper>, t: Throwable) {
                    loginResponse.postValue(null)
                }

            })
        }
        return loginResponse
    }


}