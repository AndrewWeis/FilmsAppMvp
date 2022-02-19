package com.example.filmsapp.data.network.entities

import com.example.filmsapp.data.entities.Film

/**
 * Дата класс, содержащий в себе фильмы, полученные с сервера
 */
data class FilmResponse(
    val films: List<Film>?
)