package com.bca.music.player.core.data.network.api


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class MusicPlayerInterceptor @Inject constructor(

) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        fun getRequestHeader(chain: Interceptor.Chain): Request {
            return chain.request()
                .newBuilder()
                .apply {

                }.build()
        }

        val originRequest: Request = getRequestHeader(chain)
        val originResponse: Response = chain.proceed(originRequest)

        if (originResponse.code == 401) {
            synchronized(this) {
//                if () {
//                    return chain.proceed(getRequestHeader(chain))
//                }
            }
        }
        return originResponse
    }


}

