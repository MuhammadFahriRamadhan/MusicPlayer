package com.bca.music.player.core.di

import com.bca.music.player.core.data.network.api.SearchApi
import com.bca.music.player.core.data.repositoryimpl.SearchRepositoryImpl
import com.bca.music.player.core.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(searchApi: SearchApi): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }


}
