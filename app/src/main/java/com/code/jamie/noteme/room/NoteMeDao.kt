package com.code.jamie.noteme.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.code.jamie.noteme.NoteMe
import com.code.jamie.noteme.models.NoteDB

@Dao
interface NoteMeDao {
    @Insert
    suspend fun saveNote(note:NoteDB):NoteDB
    @Query("select * from note order by id asc")
    suspend fun getAllNotes():List<NoteDB>
    @Update
    suspend fun updateNote(note:NoteDB):NoteDB
    @Delete
    suspend fun deleteNote(note:NoteDB)
}