package com.example.filmsapp.ui.data.entities

/**
 * Данные обходимые для [ListItem] жанра
 */
data class GenreData(
    val id: Int,
    var genre: String,
    var isSelected: Boolean
)

/**
 * Данные обходимые для [ListItem] заголовка
 */
data class HeaderData(
    var title: String
)