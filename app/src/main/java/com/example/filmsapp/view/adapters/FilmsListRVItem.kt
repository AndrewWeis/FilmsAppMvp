package com.example.filmsapp.view.adapters

import com.example.filmsapp.smartTruncate
import com.squareup.moshi.Json

sealed class FilmsListRVItem {

    data class Title(
        val title: String
    ) : FilmsListRVItem()

    data class Film(
        val id: Long?,
        @Json(name = "localized_name") val localName: String?,
        val name: String?,
        val year: Int?,
        val rate: Double?,
        @Json(name = "image_url") val imageUrl: String?,
        val description: String?,
        val genres: List<String>?,

        /**
         * Short description is used for displaying truncated descriptions in the UI
         */
        val shortTitle: String? = localName?.smartTruncate(16)

    ) : FilmsListRVItem()

    data class Genre(
        val name: String,
        val isSelected: Boolean = false
    ) : FilmsListRVItem()

}