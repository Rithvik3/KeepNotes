package com.example.keepnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    private val repo : NoteRepository
    val allNotes : LiveData<List<Note>>
    init {
        val Dao = NoteDatabase.getInstance(application).getNoteDao
        repo = NoteRepository(Dao)
        allNotes = repo.allNotes
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repo.delete(note)
    }

    fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
}