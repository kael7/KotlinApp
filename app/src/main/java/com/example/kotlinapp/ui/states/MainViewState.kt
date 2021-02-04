package com.example.kotlinapp.ui.states

import com.example.kotlinapp.model.Note
import com.example.kotlinapp.ui.states.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)