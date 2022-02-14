package com.example.filmsapp.ui.list.generators

import com.example.filmsapp.R
import com.example.filmsapp.data.network.entities.Film
import com.example.filmsapp.ui.data.entities.*
import com.example.utils.ResourcesUtils.getString

/**
 * Класс для генерации элементов списка
 */
class FilmsGenerator {

    /**
     * Создает список [ListItem]'ов
     *
     * @param films список фильмов
     * @param genres список жанров
     * @param selectedGenreId идентификатор выбранного жанра
     */
    fun generateListItems(
        films: List<Film>,
        genres: List<String>,
        selectedGenreId: Int?
    ): List<ListItem> {

        val listItems: MutableList<ListItem> = mutableListOf()

        addGenresHeaderToListItems(listItems)
        addGenresToListItems(listItems, genres, selectedGenreId)
        addFilmsHeaderToListItems(listItems)
        addFilmsToListItems(listItems, films)

        return listItems
    }

    /**
     * Добавляет заголовок для фильмов в список [ListItem]'ов
     *
     * @param listItems элементы списка
     */
    private fun addFilmsHeaderToListItems(listItems: MutableList<ListItem>) {
        val filmsHeaderSettings = Settings(
            leftMargin = 0f,
            rightMargin = 0f,
            topMargin = 16f,
            bottomMargin = 8f
        )

        addHeaderToListItems(
            listItems = listItems,
            type = ListItemTypes.FILMS_HEADER,
            stringRes = R.string.title_films,
            settings = filmsHeaderSettings
        )
    }

    /**
     * Добавляет заголовок для жанров в список [ListItem]'ов
     *
     * @param listItems элементы списка
     */
    private fun addGenresHeaderToListItems(listItems: MutableList<ListItem>) {
        val genresHeaderSettings = Settings(
            leftMargin = 0f,
            rightMargin = 0f,
            topMargin = 8f,
            bottomMargin = 0f
        )

        addHeaderToListItems(
            listItems = listItems,
            type = ListItemTypes.GENRES_HEADER,
            stringRes = R.string.title_genres,
            settings = genresHeaderSettings
        )
    }

    /**
     * Добавляет заголовок в список [ListItem]'ов
     *
     * @param listItems элементы списка
     * @param type тип заголовка
     * @param stringRes строковый ресурс для заголовока
     * @param settings настройки заголовка
     */
    private fun addHeaderToListItems(
        listItems: MutableList<ListItem>,
        type: ListItemTypes?,
        stringRes: Int,
        settings: Settings
    ) {
        listItems.add(
            ListItem(
                type = type,
                data = HeaderData(getString(stringRes)),
                settings = settings
            )
        )
    }

    /**
     * Добавляет жанры в список [ListItem]'ов
     *
     * @param listItems элементы списка
     * @param genres список жанров
     * @param selectedGenreId идентификатор выбранного жанра
     */
    private fun addGenresToListItems(
        listItems: MutableList<ListItem>,
        genres: List<String>,
        selectedGenreId: Int?
    ) {
        for (i in genres.indices) {
            if (selectedGenreId != null && genres[selectedGenreId] == genres[i]) {
                addGenreToGenresListItems(listItems, genres[i], i, true)
            } else {
                addGenreToGenresListItems(listItems, genres[i], i, false)
            }
        }
    }

    /**
     * Добавляет фильмы в список [ListItem]'ов
     *
     * @param listItems элементы списка
     * @param films список фильмов
     */
    private fun addFilmsToListItems(
        listItems: MutableList<ListItem>,
        films: List<Film>,
    ) {

        val leftFilmSettings = Settings(
            leftMargin = 16f,
            rightMargin = 8f,
            topMargin = 0f,
            bottomMargin = 16f
        )

        val rightFilmSettings = Settings(
            leftMargin = 8f,
            rightMargin = 16f,
            topMargin = 0f,
            bottomMargin = 16f
        )

        for (i in films.indices) {
            if (i % 2 == 0) {
                addFilmToListItems(listItems, films[i], leftFilmSettings)
            } else {
                addFilmToListItems(listItems, films[i], rightFilmSettings)
            }
        }
    }

    /**
     * Добавляет жанр в список [ListItem]'ов
     *
     * @param listItems элементы списка
     * @param genre жанр
     * @param genreId идентификатор жанра
     */
    private fun addGenreToGenresListItems(
        listItems: MutableList<ListItem>,
        genre: String,
        genreId: Int,
        isSelected: Boolean
    ) {
        val formattedGenre = genre.replaceFirstChar { it.uppercase() }

        listItems.add(
            ListItem(
                type = ListItemTypes.GENRE,
                data = GenreData(genreId, formattedGenre, isSelected)
            )
        )
    }

    /**
     * Добавляет фильм в список [ListItem]'ов
     *
     * @param listItems элементы списка
     * @param film фильм
     * @param settings настройки для фильма
     */
    private fun addFilmToListItems(
        listItems: MutableList<ListItem>,
        film: Film,
        settings: Settings
    ) {
        listItems.add(
            ListItem(
                type = ListItemTypes.FILM,
                data = film,
                settings = settings
            )
        )
    }
}