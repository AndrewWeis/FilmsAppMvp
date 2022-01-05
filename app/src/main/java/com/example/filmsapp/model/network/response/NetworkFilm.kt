package com.example.filmsapp.model.network.response

import com.squareup.moshi.Json

data class NetworkFilm(
    val id: Long?,
    @Json(name = "localized_name") val localName: String?,
    val name: String?,
    val year: Int?,
    val rating: Double?,
    @Json(name = "image_url") val imageUrl: String?,
    val description: String?,
    val genres: List<String>?
)