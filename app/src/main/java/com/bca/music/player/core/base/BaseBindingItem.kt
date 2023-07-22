package com.bca.music.player.core.base

import androidx.viewbinding.ViewBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import io.reactivex.disposables.CompositeDisposable

abstract class BaseBindingItem<VB : ViewBinding> : AbstractBindingItem<VB>() {

    protected val disposable: CompositeDisposable by lazy { CompositeDisposable() }
}