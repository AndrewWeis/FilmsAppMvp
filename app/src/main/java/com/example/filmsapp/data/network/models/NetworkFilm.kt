package com.example.filmsapp.data.network.models

import com.google.gson.annotations.SerializedName

/**
 * Дата класс для представления фильма, полученного в виде JSON
 */
data class NetworkFilm(
    val id: Long?,
    @SerializedName("localized_name") val localName: String?,
    val name: String?,
    val year: Int?,
    @SerializedName("rating") val rate: Float?,
    @SerializedName("image_url") val imageUrl: String?,
    val description: String?,
    val genres: List<String>?,
)
