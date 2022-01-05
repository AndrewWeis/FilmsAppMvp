package com.example.filmsapp.view.adapters

import com.squareup.moshi.Json

sealed class FilmsListRVItem {

    class Title(
        val title: String
    ) : FilmsListRVItem()

    class Film(
        val id: Long?,
        @Json(name = "localized_name") val localName: String?,
        val name: String?,
        val year: Int?,
        val rating: Double?,
        @Json(name = "image_url") val imageUrl: String?,
        val description: String?,
        val genres: List<String>?
    ) : FilmsListRVItem()

    class Genre(
        val name: String,
    ) : FilmsListRVItem()

}