package com.example.kotlinapp.model

import com.example.kotlinapp.model.providers.FireStoreProvider
import com.example.kotlinapp.model.providers.RemoteDataProvider

object Repository {
    private val remoteProvider: RemoteDataProvider =
        FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}