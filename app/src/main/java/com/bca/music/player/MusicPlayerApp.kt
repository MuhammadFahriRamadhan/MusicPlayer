package com.bca.music.player

import com.bca.music.player.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MusicPlayerApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@MusicPlayerApp) }
    }
}