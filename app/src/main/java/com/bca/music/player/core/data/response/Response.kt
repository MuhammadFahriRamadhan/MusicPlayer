package id.altea.care.core.data.response

import com.google.gson.annotations.SerializedName


open class Response<T>(
    @SerializedName("resultCount")
    val resultCount : Int?,
    @SerializedName("results")
    val data : T)
