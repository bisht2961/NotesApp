package com.bisht2961.noteapp.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.bisht2961.noteapp.Database.Note
import com.bisht2961.noteapp.Database.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:Flow<List<Note>> = noteDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note:Note){
        noteDao.insert(note)
    }
    @WorkerThread
    suspend fun delete(note:Note){
        noteDao.delete(note)
    }
    @WorkerThread
    suspend fun deleteAll(){
        noteDao.deleteAll()
    }
    @WorkerThread
    suspend fun update(note:Note){
        noteDao.update(note)
    }

    fun getAllNotesByTitle(title:String):Flow<List<Note>>{
        return noteDao.getNotesByTitle(title)
    }
}