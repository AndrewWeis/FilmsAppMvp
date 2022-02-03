package com.example.filmsapp.data.network.models

/**
 * Дата класс, содержащий в себе фильмы, полученные с сервера
 */
data class FilmResponse(
    val films: List<NetworkFilm>?
)