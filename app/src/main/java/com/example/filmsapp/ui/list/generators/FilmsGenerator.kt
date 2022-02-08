package com.example.filmsapp.ui.list.generators

import com.example.filmsapp.R
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.list.entities.FilmHeader
import com.example.filmsapp.ui.list.entities.GenreData
import com.example.filmsapp.ui.list.entities.GenreHeader
import com.example.filmsapp.ui.list.entities.ListItem
import com.example.filmsapp.ui.list.entities.Settings
import com.example.filmsapp.ui.utils.ResourcesUtils.getString
import com.example.filmsapp.ui.utils.firstCharToLowerCase
import com.example.filmsapp.ui.utils.firstCharToUpperCase

/**
 * Класс для генерации элементов списка
 */
class FilmsGenerator {
    val genresHeader =
        ListItem(
            data = GenreHeader(getString(R.string.title_genres)),
            settings = Settings(0f, 0f, 8f, 0f)
        )

    val filmsHeader =
        ListItem(
            data = FilmHeader(getString(R.string.title_films)),
            settings = Settings(0f, 0f, 16f, 8f)
        )

    var genresList: MutableList<ListItem> = mutableListOf()
    var filmsList: MutableList<ListItem> = mutableListOf()

    fun generateList(films: List<Film>, selectedGenre: GenreData?) {
        generateGenresList(films, selectedGenre)
        generateFilmsList(films, selectedGenre)
    }

    /**
     * Преобразовывает лист фильмов полученных с сервера в лист [ListItem]'ов
     * для отображения жанров.
     */
    private fun generateGenresList(films: List<Film>, selectedGenre: GenreData?) {
        genresList = mutableListOf()
        val genresFromFilms: MutableSet<String> = sortedSetOf()

        films.forEach { film ->
            film.genres?.forEach { genre ->
                genresFromFilms.add(genre)
            }
        }

        genresFromFilms.forEach {
            val genre = it.firstCharToUpperCase()

            if (selectedGenre != null && selectedGenre.genre == genre) {
                genresList.add(ListItem(data = GenreData(genre, true)))
            } else {
                genresList.add(ListItem(data = GenreData(genre, false)))
            }
        }
    }

    /**
     * Преобразовывает лист фильмов полученных с сервера в лист [ListItem]'ов
     * для отображения фильмов.
     */
    private fun generateFilmsList(films: List<Film>, selectedGenre: GenreData?) {
        filmsList = mutableListOf()

        var sortedFilms = films.sortedBy { it.localizedName }

        if (selectedGenre != null) {
            sortedFilms = sortedFilms.filter { film ->
                film.genres?.contains(selectedGenre.genre.firstCharToLowerCase()) == true
            }
        }

        for (i in sortedFilms.indices) {
            if (i % 2 == 0) {
                filmsList.add(
                    ListItem(
                        data = sortedFilms[i],
                        settings = Settings(16f, 8f, 0f, 16f)
                    )
                )
            } else {
                filmsList.add(
                    ListItem(
                        data = sortedFilms[i],
                        settings = Settings(8f, 16f, 0f, 16f)
                    )
                )
            }
        }
    }
}