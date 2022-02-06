package com.example.filmsapp.ui.list

class FilmsGenerator {
    val genresHeader =
        ListItem(data = GenreHeader("Жанры"), settings = Settings(0f, 0f, 8f, 8f))

    val filmsHeader =
        ListItem(data = FilmHeader("Фильмы"), settings = Settings(0f, 0f, 16f, 16f))

    val genres = listOf(
        ListItem(data = Genre("Жанр 1", false)),
        ListItem(data = Genre("Жанр 2", false)),
        ListItem(data = Genre("Жанр 3", true))
    )

    val films = listOf(
        ListItem(
            data = Film(null, null, "Фильм 1", null, null, null, null, null),
            settings = Settings(16f, 8f, 0f, 16f)
        ),
        ListItem(
            data = Film(null, null, "Фильм 2", null, null, null, null, null),
            settings = Settings(8f, 16f, 0f, 16f)
        ),
        ListItem(
            data = Film(null, null, "Фильм 3", null, null, null, null, null),
            settings = Settings(16f, 8f, 0f, 16f)
        ),
        ListItem(
            data = Film(null, null, "Фильм 4", null, null, null, null, null),
            settings = Settings(8f, 16f, 0f, 16f)
        ),
    )

    fun generateListItems() {

    }
}