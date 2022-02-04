package com.example.filmsapp.ui.list.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Класс для отображения элементов RecyclerView
 */
sealed class FilmsListRVItem {

    /**
     * Заголовок
     */
    data class Title(
        val title: String
    ) : FilmsListRVItem()

    /**
     * Фильм
     */
    @Parcelize
    data class Film(
        val id: Long?,
        val localName: String?,
        val name: String?,
        val year: Int?,
        val rate: String?,
        val imageUrl: String?,
        val description: String?,
        val genres: List<String>?,
    ) : FilmsListRVItem(), Parcelable

    /**
     * Жанр
     */
    data class Genre(
        val name: String,
        val isSelected: Boolean = false
    ) : FilmsListRVItem()
}