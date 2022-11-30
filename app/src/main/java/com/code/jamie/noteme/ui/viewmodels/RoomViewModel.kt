package com.code.jamie.noteme.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.code.jamie.noteme.db.NoteMeDao
import com.code.jamie.noteme.db.RoomRepo
import com.code.jamie.noteme.models.vo.NoteDB
import com.code.jamie.noteme.models.vo.User
import com.code.jamie.noteme.models.vo.UserDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepo: RoomRepo,
    application: Application
) : AndroidViewModel(application) {
    private var user: MutableLiveData<List<UserDB>> = MutableLiveData()
    var notes:MutableLiveData<List<NoteDB>> = MutableLiveData()
    fun saveUser(userDB: UserDB) {
        viewModelScope.launch {
            roomRepo.saveUser(userDB)
        }
    }

    fun getUser(): LiveData<List<UserDB>> {
        viewModelScope.launch {
            user.postValue(roomRepo.getUser())
        }
        return user
    }
    fun updateUser(userDB: UserDB){
        viewModelScope.launch {
            roomRepo.updateUser(userDB)
        }
    }
    fun deleteUser(user: UserDB){
        viewModelScope.launch {
            roomRepo.deleteUser(user)
        }
    }
    fun saveNote(noteDB: NoteDB){
        viewModelScope.launch {
            roomRepo.saveNote(noteDB)
        }
    }
    fun getNotes():LiveData<List<NoteDB>>{
        viewModelScope.launch {
            notes.postValue(roomRepo.getNotes())
        }
        return notes
    }
    fun updateNote(noteDB: NoteDB){
        viewModelScope.launch {
            roomRepo.updateNote(noteDB)
        }
    }
    fun deleteNote(noteDB: NoteDB){
        viewModelScope.launch {
            roomRepo.deleteNote(noteDB)
        }
    }

}