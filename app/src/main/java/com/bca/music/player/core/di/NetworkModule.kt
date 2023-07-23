package com.bca.music.player.core.di

import android.util.Log
import com.bca.music.player.MusicPlayerApp
//import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import com.bca.music.player.core.data.network.api.MusicPlayerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.bca.music.player.BuildConfig

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitPublic(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(musicPlayerInterceptor: MusicPlayerInterceptor, application : MusicPlayerApp): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(musicPlayerInterceptor)
//            .addInterceptor(ChuckerInterceptor(application))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor())
                    addNetworkInterceptor(StethoInterceptor())
                }
            }.build()
    }



    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("Retrofit Profiler", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY

        }
    }

}
