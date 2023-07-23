package com.bca.music.player.core.data.response

import androidx.annotation.Keep
import com.bca.music.player.core.domain.model.SearchResultItem
import com.google.gson.annotations.SerializedName

@Keep
data class SearchResultResponseItem(
    @SerializedName("artistId")
    val artistId: Int?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String?,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String?,
    @SerializedName("collectionId")
    val collectionId: Int?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("collectionPrice")
    val collectionPrice: Double?,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("discCount")
    val discCount: Int?,
    @SerializedName("discNumber")
    val discNumber: Int?,
    @SerializedName("isStreamable")
    val isStreamable: Boolean?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?,
    @SerializedName("trackCount")
    val trackCount: Int?,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String?,
    @SerializedName("trackId")
    val trackId: Int?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("trackNumber")
    val trackNumber: Int?,
    @SerializedName("trackPrice")
    val trackPrice: Double?,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int?,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String?,
    @SerializedName("wrapperType")
    val wrapperType: String?
) {
    fun toSearchResultItem() : SearchResultItem {
        return SearchResultItem(
         artistId,
         artistName,
         artistViewUrl,
         artworkUrl100,
         artworkUrl30,
         artworkUrl60,
         collectionCensoredName,
         collectionExplicitness,
         collectionId,
         collectionName,
         collectionPrice,
         collectionViewUrl,
         country,
         currency,
         discCount,
         discNumber,
         isStreamable,
         kind,
         previewUrl,
         primaryGenreName,
         releaseDate,
         trackCensoredName,
         trackCount,
         trackExplicitness,
         trackId,
         trackName,
         trackNumber,
         trackPrice,
         trackTimeMillis,
         trackViewUrl,
         wrapperType
        )
    }
}