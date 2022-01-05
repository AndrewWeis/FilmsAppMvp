package com.example.filmsapp.model.network.response

import com.example.filmsapp.view.adapters.FilmsListRVItem

data class FilmResponse (
    val films: List<FilmsListRVItem.Film>?
)