package com.code.jamie.noteme.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.code.jamie.noteme.models.Note

@Dao
interface NoteMeDao {
    @Insert
    suspend fun saveNote(note:Note): Note
    @Query("select * from note order by id desc")
    suspend fun getAllNotes():List<Note>
    @Update
    suspend fun updateNote(note: Note):Note
    @Delete
    suspend fun deleteNote(note: Note)
}