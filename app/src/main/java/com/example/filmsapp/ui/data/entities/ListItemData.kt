package com.example.filmsapp.ui.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Данные обходимые для [ListItem] жанра
 */
data class GenreData(
    var genre: String,
    var isSelected: Boolean
)

/**
 * Данные обходимые для [ListItem] заголовка
 */
data class HeaderData(
    var title: String
)

/**
 * Данные обходимые для [ListItem] фильма
 */
@Parcelize
data class Film(
    var id: Long?,
    @SerializedName("localized_name")
    var localizedName: String?,
    var name: String?,
    var year: String?,
    var rating: Float?,
    @SerializedName("image_url")
    var imageUrl: String?,
    var description: String?,
    var genres: List<String>?
) : Parcelable