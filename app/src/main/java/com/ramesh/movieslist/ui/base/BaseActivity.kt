package com.ramesh.movieslist.ui.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import android.os.*
import com.ramesh.movieslist.ui.MainActivity

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }


    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }


    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        resultLauncher.launch(intent)

    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

        }

    fun showMessage(message: String) =
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    protected abstract fun provideLayoutId(): View

    protected abstract fun setupView(savedInstanceState: Bundle?)

    val updateHandler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        updateDisplay() // some action(s)
    }

    private fun updateDisplay() {
        navigate()
    }

    private fun navigate() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        updateHandler.removeCallbacks(runnable)
        super.onStop()
    }

}