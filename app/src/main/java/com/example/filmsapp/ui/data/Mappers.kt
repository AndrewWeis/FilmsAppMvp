package com.example.filmsapp.ui.data

import com.example.filmsapp.data.network.models.NetworkFilm
import com.example.filmsapp.ui.list.models.FilmsListRVItem

fun List<NetworkFilm>.toDomain(): List<FilmsListRVItem.Film> {
    return map {
        FilmsListRVItem.Film(
            id = it.id,
            localName = it.localName,
            name = it.name,
            year = it.year,
            rate = it.rate?.toString()?.dropLast(2) ?: "0.0",
            imageUrl = it.imageUrl,
            description = it.description ?: "Нет описания",
            genres = it.genres
        )
    }
}