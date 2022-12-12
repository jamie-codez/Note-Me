package com.code.jamie.noteme.db

import com.code.jamie.noteme.models.vo.NoteDB
import com.code.jamie.noteme.models.vo.UserDB
import javax.inject.Inject

class RoomRepo @Inject constructor(private val noteMeDao: NoteMeDao) {
    suspend fun saveUser(userDB: UserDB) = noteMeDao.saveUser(userDB)
    suspend fun getUser(): List<UserDB> = noteMeDao.getUser()
    suspend fun updateUser(userDB: UserDB) = noteMeDao.updateUser(userDB)
    suspend fun updateUserDB(userDB: UserDB) = noteMeDao.updateUserDB(
        userDB._id,
        userDB.email,
        userDB.username,
        userDB.imageUrl,
        userDB.password,
        userDB.verified
    )

    suspend fun deleteUser(userDB: UserDB) = noteMeDao.deleteUser(userDB)
    suspend fun saveNote(noteDB: NoteDB) = noteMeDao.saveNote(noteDB)
    suspend fun getNotes() = noteMeDao.getNotes()
    suspend fun updateNote(noteDB: NoteDB) = noteMeDao.updateNote(noteDB)
    suspend fun deleteNote(noteDB: NoteDB) = noteMeDao.deleteNote(noteDB)
}