package com.bca.music.player.core.di


import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.bca.music.player.core.di.viewmodel.ViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


}