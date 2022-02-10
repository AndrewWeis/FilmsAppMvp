package com.example.filmsapp.ui.list.generators

import com.example.filmsapp.R
import com.example.filmsapp.ui.data.entities.*
import com.example.utils.ResourcesUtils.getString

/**
 * Класс для генерации элементов списка
 */
class FilmsGenerator {

    fun generateListItems(films: List<Film>, selectedGenre: GenreData?): List<ListItem> {

        val genresHeaderSettings = Settings(
            leftMargin = 0f,
            rightMargin = 0f,
            topMargin = 8f,
            bottomMargin = 0f
        )

        val filmsHeaderSettings = Settings(
            leftMargin = 0f,
            rightMargin = 0f,
            topMargin = 16f,
            bottomMargin = 8f
        )

        val listItems: MutableList<ListItem> = mutableListOf()

        listItems.add(
            ListItem(
                type = ListItemTypes.GENRES_HEADER,
                data = HeaderData(getString(R.string.title_genres)),
                settings = genresHeaderSettings
            )
        )

        val genresListItems = generateGenreListItems(films, selectedGenre)
        listItems.addAll(genresListItems)

        listItems.add(
            ListItem(
                type = ListItemTypes.FILMS_HEADER,
                data = HeaderData(getString(R.string.title_films)),
                settings = filmsHeaderSettings
            )
        )

        val filmsListItems = generateFilmsListItems(films, selectedGenre)
        listItems.addAll(filmsListItems)

        return listItems
    }

    /**
     * Преобразовывает лист фильмов полученных с сервера в лист [ListItem]'ов
     * для отображения жанров.
     *
     * @param films список фильмов с сервера
     * @param selectedGenre выбранный жанр
     */
    private fun generateGenreListItems(
        films: List<Film>,
        selectedGenre: GenreData?
    ): List<ListItem> {
        val genresList: MutableList<ListItem> = mutableListOf()

        val genresFromFilms: MutableSet<String> = sortedSetOf()

        films.forEach { film ->
            film.genres?.forEach { genre ->
                genresFromFilms.add(genre)
            }
        }

        genresFromFilms.forEach {
            val genre = firstCharToUpperCase(it)

            if (selectedGenre != null && selectedGenre.genre == genre) {
                genresList.add(
                    ListItem(
                        type = ListItemTypes.GENRE,
                        data = GenreData(genre, true)
                    )
                )
            } else {
                genresList.add(
                    ListItem(
                        type = ListItemTypes.GENRE,
                        data = GenreData(genre, false)
                    )
                )
            }
        }

        return genresList
    }

    /**
     * Преобразовывает лист фильмов полученных с сервера в лист [ListItem]'ов
     * для отображения фильмов.
     *
     * @param films список фильмов с сервера
     * @param selectedGenre выбранный жанр
     */
    private fun generateFilmsListItems(
        films: List<Film>,
        selectedGenre: GenreData?
    ): List<ListItem> {
        val filmsList: MutableList<ListItem> = mutableListOf()

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
                        type = ListItemTypes.FILM,
                        data = sortedFilms[i],
                        settings = Settings(16f, 8f, 0f, 16f)
                    )
                )
            } else {
                filmsList.add(
                    ListItem(
                        type = ListItemTypes.FILM,
                        data = sortedFilms[i],
                        settings = Settings(8f, 16f, 0f, 16f)
                    )
                )
            }
        }

        return filmsList
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