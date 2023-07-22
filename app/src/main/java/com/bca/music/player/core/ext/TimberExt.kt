package com.bca.music.player.core.ext

import com.bca.music.player.BuildConfig
import timber.log.Timber

object TimberExt {
    fun d(message: String?) {
        if(!BuildConfig.DEBUG) return
        Timber.d(message)
    }

    fun d(t: Throwable) {
        if(!BuildConfig.DEBUG) return
        Timber.d(t)
    }

    fun d(message: String, args: Any) {
        if(!BuildConfig.DEBUG) return
        Timber.d(message, args)
    }

    fun e(message: String?) {
        if(!BuildConfig.DEBUG) return
        Timber.e(message)
    }

    fun e(t: Throwable) {
        if(!BuildConfig.DEBUG) return
        Timber.e(t)
    }

    fun e(message: String, args: Any) {
        if(!BuildConfig.DEBUG) return
        Timber.e(message, args)
    }

    fun i(message: String?) {
        if(!BuildConfig.DEBUG) return
        Timber.i(message)
    }

    fun i(t: Throwable) {
        if(!BuildConfig.DEBUG) return
        Timber.i(t)
    }

    fun i(message: String, args: Any) {
        if(!BuildConfig.DEBUG) return
        Timber.i(message, args)
    }

    fun w(message: String?) {
        if(!BuildConfig.DEBUG) return
        Timber.w(message)
    }

    fun w(t: Throwable) {
        if(!BuildConfig.DEBUG) return
        Timber.w(t)
    }

    fun w(message: String, args: Any) {
        if(!BuildConfig.DEBUG) return
        Timber.w(message, args)
    }

    fun print(message: String) {
        if(!BuildConfig.DEBUG) return
        println(message)
    }

    fun plant() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return if(!BuildConfig.DEBUG) {
                    ""
                } else {
                    String.format("(%s:%s)#%s", element.fileName, element.lineNumber, element.methodName)
                }
            }
        })
    }
}