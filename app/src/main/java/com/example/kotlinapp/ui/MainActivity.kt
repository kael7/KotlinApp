package com.example.kotlinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.databinding.ActivityMainBinding
import com.example.kotlinapp.model.Note
import com.example.kotlinapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override val layoutRes: Int = com.example.kotlinapp.R.layout.activity_main
    override val ui: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(ui.toolbar)

        adapter = MainAdapter(object : OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        ui.mainRecycler.adapter = adapter

        ui.fab.setOnClickListener { openNoteScreen() }
    }

    private fun openNoteScreen(note: Note? = null) {
        startActivity(NoteActivity.getStartIntent(this, note?.id))
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }
}