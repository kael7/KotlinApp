package com.example.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.model.Note
import com.example.kotlinapp.model.NoteResult
import com.example.kotlinapp.model.Repository
import com.example.kotlinapp.ui.MainViewState

class MainViewModel(val repository: Repository = Repository) :
    BaseViewModel<List<Note>?, MainViewState>() {

    private val repositoryNotes = repository.getNotes()
    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}