package com.example.kotlinapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.kotlinapp.R
import com.example.kotlinapp.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int
    abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)
        viewModel.getViewState().observe(this, object : Observer<S> {
            override fun onChanged(t: S?) {
                if (t == null) return
                if (t.data != null) renderData(t.data!!)
                if (t.error != null) renderError(t.error)
            }
        })
    }

    protected fun renderError(error: Throwable) {
        if (error.message != null) showError(error.message!!)
    }

    abstract fun renderData(data: T)

    protected fun showError(error: String) {
        //TODO
    }
}