package com.bisht2961.noteapp.ViewModel

import androidx.lifecycle.*
import com.bisht2961.noteapp.Database.Note
import com.bisht2961.noteapp.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(private val repository: NoteRepository):ViewModel() {

    val allNotes:LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun insert(note:Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun deleteAll() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAll()
    }

    fun update(note:Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }

    fun getAllByTitle(title:String):LiveData<List<Note>>{
        val listByTitle:LiveData<List<Note>> = repository.getAllNotesByTitle(title).asLiveData()
        return listByTitle
    }
}
class NoteViewModelFactory(private val repository: NoteRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}