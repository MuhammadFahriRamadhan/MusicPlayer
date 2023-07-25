package com.bca.music.player.core.ext

import com.bca.music.player.core.exception.Failure
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.getGeneralErrorServer(): Failure { TimberExt.e(this)
    return when (this) {
        is HttpException -> {
            try {
                if (this.code() >= 500) {
                    return Failure.ServerError("Terjadi kesalahan server, mohon coba kembali nanti")
                }
                if (this.code() == 401) {
                    return Failure.ExpiredSession
                }
                Failure.NotFound("Terjadi kesalahan, url tidak valid atau tidak ditemukan")
            } catch (e: Exception) {
                Failure.ServerError("Terjadi kesalahan, mohon coba kembali nanti")
            }
        }
        is SocketTimeoutException -> Failure.ServerError("Waktu tunggu berakhir, mohon coba kembali")
        is IOException -> Failure.NetworkConnection
        else -> Failure.ServerError("Terjadi kesalahan, mohon coba kembali nanti")
    }
}
