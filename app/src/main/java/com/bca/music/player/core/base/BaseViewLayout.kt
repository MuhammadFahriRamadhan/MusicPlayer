package com.bca.music.player.core.base

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding

abstract class BaseViewLayout<VB : ViewBinding> constructor(context: Context, attributeSet: AttributeSet? = null, defStyle  : Int = 0, defStyleRes : Int = 0) : ConstraintLayout(context,attributeSet,defStyle,defStyleRes){

    var viewBinding: VB? = null

    fun setupView(width : Int,height : Int) {
        viewBinding = getUiBinding()
        val params  = LayoutParams(width, height);
        layoutParams = params
        viewBinding?.root?.layoutParams = params
        onViewReady(viewBinding)
        initUiListener()
    }

    abstract fun getUiBinding(): VB
    abstract fun onViewReady(viewBinding: VB?)
    abstract fun initUiListener()

}