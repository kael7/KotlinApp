package com.example.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.model.Repository
import com.example.kotlinapp.ui.MainViewState

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        Repository.getNotes().observeForever { notes ->
            viewStateLiveData.value =
                viewStateLiveData.value?.copy(notes = notes!!) ?: MainViewState(notes!!)
        }
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}