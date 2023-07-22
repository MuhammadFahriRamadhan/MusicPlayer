package com.bca.music.player.core.ext

import android.app.Activity
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.bca.music.player.R
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun Button.changeStateButton(state: Boolean) {
    this.isEnabled = state
}

// using throttleFirst for preventing multiple click
fun View.onSingleClick(timeMillis: Long = 600): Observable<Unit> {
    return this.clicks().throttleFirst(timeMillis, TimeUnit.MILLISECONDS)
}

fun TextView.emptyViewValidator(onNext: (View, Boolean) -> Unit): Observable<Boolean> {
    return this.textChanges().map { it.isNotEmpty() }.doOnNext { onNext(this, it) }
}

fun LayoutInflater.cloneDefaultTheme(activity: Activity): LayoutInflater {
    val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_MusicPlayer)
    return this.cloneInContext(contextThemeWrapper)
}

fun View.setLayoutParamsHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

