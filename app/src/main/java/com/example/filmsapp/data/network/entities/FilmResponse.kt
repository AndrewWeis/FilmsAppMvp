package com.example.filmsapp.data.network.entities

/**
 * Дата класс, содержащий в себе фильмы, полученные с сервера
 */
data class FilmResponse(
    val films: List<Film>?
)