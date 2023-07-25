package com.bca.music.player.core.di

import com.bca.music.player.MusicPlayerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<MusicPlayerApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MusicPlayerApp): Builder

        fun build(): AppComponent
    }
}
