package com.code.jamie.noteme.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.code.jamie.noteme.api.NoteMeRepo
import com.code.jamie.noteme.api.NoteMeService
import com.code.jamie.noteme.vo.models.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application):AndroidViewModel(application) {

    private val jwt:MutableLiveData<String> = MutableLiveData()
    private val noteMeRepo:NoteMeRepo by lazy { NoteMeRepo() }
    fun register(user: User):String{
        viewModelScope.launch {
            val call = noteMeRepo.register(user)
            call.enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful){
                        //TODO Snackbar or  Toast
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}