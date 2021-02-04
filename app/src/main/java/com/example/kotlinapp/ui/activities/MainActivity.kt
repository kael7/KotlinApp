package com.example.kotlinapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.ActivityMainBinding
import com.example.kotlinapp.model.Note
import com.example.kotlinapp.ui.LogoutDialog
import com.example.kotlinapp.ui.MainAdapter
import com.example.kotlinapp.ui.states.MainViewState
import com.example.kotlinapp.ui.OnItemClickListener
import com.example.kotlinapp.viewmodel.MainViewModel
import com.firebase.ui.auth.AuthUI

class MainActivity : BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    override val viewModel: MainViewModel
            by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override val ui: ActivityMainBinding
            by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val layoutRes: Int = com.example.kotlinapp.R.layout.activity_main
    private lateinit var adapter: MainAdapter

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(ui.toolbar)

        adapter = MainAdapter(object :
            OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        ui.mainRecycler.adapter = adapter

        ui.fab.setOnClickListener { openNoteScreen() }
    }

    private fun openNoteScreen(note: Note? = null) {
        startActivity(
            NoteActivity.getStartIntent(
                this,
                note?.id
            )
        )
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.menu_main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.logout -> showLogoutDialog().let { true }
            else -> false
        }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?: LogoutDialog.createInstance()
            .show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()

            }
    }
}