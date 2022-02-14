package com.example.filmsapp.mvp.presenters

import com.example.filmsapp.data.entities.Film
import com.example.filmsapp.data.network.entities.FilmResponse
import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.presenters.base.BasePresenter
import com.example.filmsapp.mvp.views.FilmsView
import moxy.InjectViewState

/**
 * Presenter для работы со списком фильмов
 */
@InjectViewState
class FilmsPresenter(
    private val filmModel: FilmModel
) : BasePresenter<FilmsView>() {

    private var films: List<Film> = emptyList()
    private var genres: List<String> = emptyList()
    private var selectedGenreId: Int? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestDataFromServer()
    }

    fun onGenreClicked(genreId: Int) {
        selectedGenreId = genreId

        val filteredFilmsList = filterFilmsByGenre()
        viewState.showFilms(filteredFilmsList, genres, selectedGenreId)
    }

    fun onRepeatButtonClicked() {
        requestDataFromServer()
    }

    private fun requestDataFromServer() {
        viewState.startContentLoading()

        filmModel.getFilms(object : FilmModel.GetFilmsCallback {

            override fun onSuccess(data: FilmResponse?) {
                films = data?.films.orEmpty()
                genres = extractGenresFromFilms()

                viewState.endContentLoading()
                viewState.showFilms(films, genres, selectedGenreId)
            }

            override fun onError(error: String) {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error)
            }
        })
    }

    /**
     * Фильтрует список фильмов по выбранному жанру
     */
    private fun filterFilmsByGenre(): List<Film> {
        var sortedFilms = films.sortedBy { it.localizedName }

        if (selectedGenreId != null) {
            sortedFilms = sortedFilms.filter { film ->
                film.genres?.contains(genres[selectedGenreId!!]) == true
            }
        }

        return sortedFilms
    }

    /**
     * Извлекает список жанров из фильмов
     */
    private fun extractGenresFromFilms(): List<String> {
        val sortedGenres: MutableSet<String> = sortedSetOf()

        films.forEach { film ->
            film.genres?.forEach { genre ->
                sortedGenres.add(genre)
            }
        }

        return sortedGenres.toList()
    }
}