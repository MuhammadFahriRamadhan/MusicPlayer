package com.bca.music.player.core.base

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import com.bca.music.player.R
import com.bca.music.player.core.exception.Failure
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment<VB : ViewBinding> : DaggerFragment() {

    var viewBinding: VB? = null
    protected val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getUiBinding()
        return viewBinding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(initMenu(), menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFirstLaunch(savedInstanceState, view)
        initUiListener()
    }

    override fun onStart() {
        super.onStart()
        onReExecute()
    }

    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(savedInstanceState: Bundle?, view: View)
    abstract fun onReExecute()
    abstract fun initUiListener()
    abstract fun initMenu(): Int

    fun getParentFm() = requireActivity().supportFragmentManager

    fun getChildFm() = childFragmentManager

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    fun showProgress() {
        (requireActivity() as BaseActivity<*>).showProgress()
    }


    fun hideProgress() {
        (requireActivity() as BaseActivity<*>).hideProgress()
    }

    fun showToast(message: String) {
        (requireActivity() as BaseActivity<*>).showToast(message)
    }

    fun showSuccessSnackBar(text: String, screenAtTop : Boolean? = false, marginTop: Int? = null, onDismissListener: (() -> Unit) = { }) {
        (requireActivity() as BaseActivity<*>).showSuccessSnackBar(text, screenAtTop,marginTop, onDismissListener)
    }

    fun showErrorSnackbar(text: String,screenAtTop : Boolean? = false, onDismissListener: (() -> Unit) = { }) {
        (requireActivity() as BaseActivity<*>).showErrorSnackbar(text, screenAtTop, onDismissListener)
    }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> (activity as BaseActivity<*>).showErrorSnackbar(
                getString(R.string.error_disconnect)
            )
            is Failure.ServerError ->{
                (activity as BaseActivity<*>).showErrorSnackbar(failure.message)

            }
            is Failure.ExpiredSession -> {
                showToast(getString(R.string.session_expired_error_toast))
            }
            else -> {
                (activity as BaseActivity<*>).showErrorSnackbar(getString(R.string.error_default))

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        disposable.clear()
    }
}
