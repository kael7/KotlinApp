package com.example.kotlinapp.ui

import com.example.kotlinapp.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) :
    BaseViewState<Note?>(note, error)