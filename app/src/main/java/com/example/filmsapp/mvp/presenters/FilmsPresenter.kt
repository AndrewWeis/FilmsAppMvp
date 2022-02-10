package com.example.filmsapp.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.filmsapp.data.network.entities.FilmResponse
import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.presenters.base.BasePresenter
import com.example.filmsapp.mvp.views.FilmsView
import com.example.filmsapp.ui.data.entities.Film
import com.example.filmsapp.ui.data.entities.GenreData

/**
 * Presenter для работы со списком фильмов
 */
@InjectViewState
class FilmsPresenter(
    private val filmModel: FilmModel
) : BasePresenter<FilmsView>() {

    private var films: List<Film> = emptyList()
    private var selectedGenre: GenreData? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestDataFromServer()
    }

    fun onGenreClicked(genre: GenreData) {
        selectedGenre = genre
        viewState.showFilms(films, selectedGenre)
    }

    fun onRepeatButtonClicked() {
        requestDataFromServer()
    }

    private fun requestDataFromServer() {
        viewState.startContentLoading()

        filmModel.getFilms(object : FilmModel.GetFilmsCallback {

            override fun onSuccess(data: FilmResponse?) {
                films = data?.films.orEmpty()

                viewState.endContentLoading()
                viewState.showFilms(films, selectedGenre)
            }

            override fun onError(error: String) {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error)
            }
        })
    }
}