package com.bca.music.player.core.di

import android.content.Context
import android.content.SharedPreferences

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class CacheModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
}
