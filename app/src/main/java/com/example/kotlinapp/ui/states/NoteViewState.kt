package com.example.kotlinapp.ui.states

import com.example.kotlinapp.model.Note
import com.example.kotlinapp.ui.states.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) :
    BaseViewState<Note?>(note, error)