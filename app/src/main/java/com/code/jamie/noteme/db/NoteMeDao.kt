package com.code.jamie.noteme.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.code.jamie.noteme.models.vo.NoteDB
import com.code.jamie.noteme.models.vo.UserDB

@Dao
interface NoteMeDao {
    /**
     * User methods
     */
    @Insert(onConflict = REPLACE)
    suspend fun saveUser(userRoom: UserDB)

    @Query("select * from user")
    suspend fun getUser(): List<UserDB>

    @Update
    suspend fun updateUser(userDB: UserDB)

    @Query(
        "update user set email=:email,username=:username,image_url=:imageUrl," +
                "password=:password,verified=:verified where id_obj=:id_obj"
    )
    suspend fun updateUserDB(
        id_obj: String,
        email: String,
        username: String,
        imageUrl: String,
        password: String,
        verified: Boolean
    )

    @Delete
    suspend fun deleteUser(userDB: UserDB)

    /**
     * Notes methods
     */
    @Insert(onConflict = REPLACE)
    suspend fun saveNote(noteDB: NoteDB)

    @Query("select * from note order by createdAt asc")
    suspend fun getNotes(): List<NoteDB>

    @Update
    suspend fun updateNote(noteDB: NoteDB)

    @Delete
    suspend fun deleteNote(noteDB: NoteDB)

}