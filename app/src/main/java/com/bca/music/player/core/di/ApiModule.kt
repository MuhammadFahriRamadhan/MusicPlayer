package com.bca.music.player.core.di

import com.bca.music.player.core.data.network.api.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}