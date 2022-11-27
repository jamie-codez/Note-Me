package com.code.jamie.noteme.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.code.jamie.noteme.models.Note
import com.code.jamie.noteme.room.RoomRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val noteMeRepository: RoomRepository):ViewModel() {
    private var notesData:MutableLiveData<List<Note>> = MutableLiveData()


}