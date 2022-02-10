package com.example.filmsapp.ui.list.generators

import com.example.filmsapp.R
import com.example.filmsapp.ui.data.entities.*
import com.example.utils.ResourcesUtils.getString

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
     *
     * @param films список фильмов с сервера
     * @param selectedGenre выбранный жанр
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
            val genre = firstCharToUpperCase(it)

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
     *
     * @param films список фильмов с сервера
     * @param selectedGenre выбранный жанр
     */
    private fun generateFilmsList(films: List<Film>, selectedGenre: GenreData?) {
        filmsList = mutableListOf()

        var sortedFilms = films.sortedBy { it.localizedName }

        if (selectedGenre != null) {
            sortedFilms = sortedFilms.filter { film ->
                film.genres?.contains(firstCharToLowerCase(selectedGenre.genre)) == true
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

    /**
     * Переводит первую букву строки в заглавную
     */
    private fun firstCharToUpperCase(str: String): String {
        return str.replaceFirstChar { it.uppercase() }
    }

    /**
     * Переводит первую букву строки в маленькую
     */
    private fun firstCharToLowerCase(str: String): String {
        return str.replaceFirstChar { it.lowercase() }
    }
}