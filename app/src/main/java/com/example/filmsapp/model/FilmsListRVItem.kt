package com.example.filmsapp.model

import android.os.Parcelable
import com.example.filmsapp.utils.smartTruncate
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

sealed class FilmsListRVItem {

    data class Title(
        val title: String
    ) : FilmsListRVItem()

    @Parcelize
    data class Film(
        val id: Long?,
        @Json(name = "localized_name") val localName: String?,
        val name: String?,
        val year: Int?,
        @Json(name = "rating") val rate: Float?,
        @Json(name = "image_url") val imageUrl: String?,
        val description: String?,
        val genres: List<String>?,
        val shortTitle: String? = localName?.smartTruncate(16)
    ) : FilmsListRVItem(), Parcelable

    data class Genre(
        val name: String,
        val isSelected: Boolean = false
    ) : FilmsListRVItem()
}