package com.example.filmsapp.ui.list

import com.google.gson.annotations.SerializedName

/**
 * Данные обходимые для [ListItem] жанра
 */
data class Genre(
    var genre: String,
    var isSelected: Boolean
)

/**
 * Данные обходимые для [ListItem] заголовка жанра
 */
data class GenreHeader(
    override var title: String
) : Header

/**
 * Данные обходимые для [ListItem] заголовка фильма
 */
data class FilmHeader(
    override var title: String
) : Header

/**
 * Данные обходимые для [ListItem] фильма
 */
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
)

/**
 * Общий класс для заголовков
 */
interface Header {
    var title: String
}