package com.bisht2961.noteapp.Application

import android.app.Application
import com.bisht2961.noteapp.Database.NoteDatabase
import com.bisht2961.noteapp.Repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {

    val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.getNoteDao())}

}