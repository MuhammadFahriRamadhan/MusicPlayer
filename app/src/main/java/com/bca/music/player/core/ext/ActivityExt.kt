package com.bca.music.player.core.ext

import android.app.Activity
import android.util.DisplayMetrics

fun Activity.getDeviceHeight(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}