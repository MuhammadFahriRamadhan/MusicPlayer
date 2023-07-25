package com.bca.music.player.core.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bca.music.player.core.di.viewmodel.ViewModelFactory
import com.bca.music.player.core.di.viewmodel.ViewModelKey
import com.bca.music.player.view.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivity(viewModel: MainActivityViewModel): ViewModel
}