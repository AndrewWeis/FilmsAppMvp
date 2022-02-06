package com.example.filmsapp.data.network.models

import com.example.filmsapp.ui.list.entities.Film

/**
 * Дата класс, содержащий в себе фильмы, полученные с сервера
 */
data class FilmResponse(
    val films: List<Film>?
)