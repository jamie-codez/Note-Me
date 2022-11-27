package com.code.jamie.noteme.room

import com.code.jamie.noteme.models.Note
import javax.inject.Inject

class RoomRepository @Inject constructor(private val noteMeDao: NoteMeDao) {
    suspend fun saveNote(note: Note):Note = noteMeDao.saveNote(note)
    suspend fun getAllNotes():List<Note> = noteMeDao.getAllNotes()
    suspend fun updateNote(note: Note):Note = noteMeDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteMeDao.deleteNote(note)
}