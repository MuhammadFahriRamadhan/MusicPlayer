package com.bca.music.player.core.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.bca.music.player.R
import com.bca.music.player.core.exception.Failure
import com.bca.music.player.core.helper.util.CustomSnackbarView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var mToolbar: Toolbar? = null
    var disposable = CompositeDisposable()
    var snackbarView: CustomSnackbarView? = null

    var viewBinding: VB? = null

    private lateinit var progress: Dialog
    private lateinit var progressReconnecting: Dialog
    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getUiBinding()
        setContentView(viewBinding?.root)
        rootView = window.decorView.findViewById(android.R.id.content)
        setupToolbar()
        onFirstLaunch(savedInstanceState)
        initProgressDialog()
        initUiListener()
    }

    abstract fun bindToolbar(): Toolbar?
    abstract fun enableBackButton(): Boolean
    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?)
    abstract fun initUiListener()

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
        viewBinding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    private fun setupToolbar() {
        bindToolbar()?.let {
            mToolbar = it
            setSupportActionBar(mToolbar)
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(enableBackButton())
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun initProgressDialog() {
        if (!::progress.isInitialized) {
            progress = Dialog(this)
            val inflate = LayoutInflater.from(this).inflate(R.layout.progress_default, null)
            progress.setContentView(inflate)
            progress.setCancelable(false)
            progress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun showProgress() {
        if (!progress.isShowing) {
            progress.show()
        }
    }

    fun hideProgress() {
        if (progress.isShowing) progress.dismiss()
    }

    fun showInfoSnackbar(
        text: String,
        screenAtTop: Boolean? = false,
        marginTop: Int? = null,
        onDismissListener: (() -> Unit) = { },
    ) {
        snackbarView = CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.INFO,
            true,
            onDismissListener
        )
        if (screenAtTop == true) {
            snackbarView?.view
            val params = snackbarView?.view?.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            params.setMargins(0, 0, 0, 0)
            params.topMargin = marginTop ?: 180
            snackbarView?.view?.layoutParams = params
            snackbarView?.show()
        } else {
            snackbarView?.show()
        }
    }




    fun showSuccessSnackBar(
        text: String,
        screenAtTop: Boolean? = false,
        marginTop: Int? = null,
        onDismissListener: (() -> Unit) = { }
    ) {
        snackbarView = CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.SUCCESS,
            true,
            dismissCallback = onDismissListener,
        )
        if (screenAtTop == true) {
            snackbarView?.view
            val params = snackbarView?.view?.layoutParams as FrameLayout.LayoutParams
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            params.setMargins(0, 0, 0, 0)
            params.gravity = Gravity.TOP
            params.topMargin = marginTop ?: 160
            snackbarView?.view?.layoutParams = params
            snackbarView?.show()
        } else {
            snackbarView?.show()
        }
    }

    fun showErrorSnackbar(
        text: String,
        screenAtTop: Boolean? = false,
        onDismissListener: (() -> Unit) = { }
    ) {
        snackbarView = CustomSnackbarView.make(
            rootView,
            text,
            CustomSnackbarView.SnackbarType.ERROR,
            true,
            dismissCallback = onDismissListener,
        )
        if (screenAtTop == true) {
            snackbarView?.view
            val params = snackbarView?.view?.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            params.setMargins(0, 0, 0, 0)
            params.topMargin = 160
            snackbarView?.view?.layoutParams = params
            snackbarView?.show()
        } else {
            snackbarView?.show()
        }
    }

    fun dismissSnackBar() {
        if (snackbarView != null && snackbarView?.isShown == true) {
            snackbarView?.dismiss()
        }
    }

    fun checkSnackbar(): CustomSnackbarView? {
        return snackbarView
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showErrorSnackbar(getString(R.string.error_disconnect))
            is Failure.ServerError -> {
                showErrorSnackbar(failure.message)
            }
            is Failure.ExpiredSession -> {
                showToast(getString(R.string.session_expired_error_toast))

            }
            else -> {
                showErrorSnackbar(getString(R.string.error_default))
            }
        }

    }
}