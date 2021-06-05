package com.bisht2961.noteapp.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query(value= "Delete from notes_table")
    suspend fun deleteAll()

    @Query(value = "Select * from notes_table Order by id ASC")
    fun getAllNotes():Flow<List<Note>>

    @Query(value = "Select * from notes_table where title Like :title")
    fun getNotesByTitle(title:String):Flow<List<Note>>

}