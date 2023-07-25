package com.bca.music.player.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.bca.music.player.R
import com.bca.music.player.core.exception.Failure
import com.bca.music.player.core.ext.cloneDefaultTheme
import com.bca.music.player.core.ext.getDeviceHeight
import com.bca.music.player.core.ext.setLayoutParamsHeight
import com.bca.music.player.core.helper.util.CustomSnackbarView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseBottomSheet<VB : ViewBinding> : BottomSheetDialogFragment() {

    protected var viewBinding: VB? = null
    protected var setCancelable: Boolean = true
    protected var setFullScreen: Boolean = false
    protected val disposable: CompositeDisposable by lazy { CompositeDisposable() }
    protected lateinit var cloneLayoutInflater: LayoutInflater

    abstract fun getUiBinding(): VB
    abstract fun onFirstLaunch(view: View, savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTopRounded)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cloneLayoutInflater = inflater.cloneDefaultTheme(requireActivity())
        if (viewBinding == null) {
            viewBinding = getUiBinding()
        }
        dialog?.setOnShowListener { dialog ->
            val layout: FrameLayout? = (dialog as BottomSheetDialog).findViewById(com.google.android.material.R.id.design_bottom_sheet)
            layout?.let {
                if(setFullScreen) it.setLayoutParamsHeight(requireActivity().getDeviceHeight())

                val behavior = BottomSheetBehavior.from(it)
                if(setFullScreen) behavior.peekHeight = requireActivity().getDeviceHeight()
                behavior.isDraggable = setCancelable
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
            }
        }
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFirstLaunch(view, savedInstanceState)
    }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.ExpiredSession -> {

            }
            else -> {}
        }
    }

    fun showErrorSnackBar(text: String, onDismissListener: (() -> Unit) = { }) {
        val snackBarView =  CustomSnackbarView.make(
            viewGroup = (viewBinding?.root?.rootView as ViewGroup),
            text = text,
            snackbarType = CustomSnackbarView.SnackbarType.ERROR,
            showClose = true,
            dismissCallback = onDismissListener,
        )
        snackBarView.show()
    }

    override fun onDestroyView() {
        disposable.dispose()
        viewBinding = null
        super.onDestroyView()
    }
}