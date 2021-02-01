package com.example.kotlinapp.ui

import com.example.kotlinapp.model.Note

class MainViewState(notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)