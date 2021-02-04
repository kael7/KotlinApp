package com.example.kotlinapp.model.providers

import androidx.lifecycle.LiveData
import com.example.kotlinapp.model.Note
import com.example.kotlinapp.model.NoteResult
import com.example.kotlinapp.model.User

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}