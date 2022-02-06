package com.example.filmsapp.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.filmsapp.data.network.models.FilmResponse
import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.presenters.base.BasePresenter
import com.example.filmsapp.mvp.views.FilmsView
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.list.entities.GenreData

/**
 * Presenter для работы со списком фильмов
 */
@InjectViewState
class FilmsPresenter(
    private val filmModel: FilmModel
) : BasePresenter<FilmsView>() {

    private var films: List<Film> = listOf()
    private var selectedGenre: GenreData? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestDataFromServer()
    }

    fun onGenreClicked(genre: GenreData) {
        selectedGenre = genre
        viewState.showData(films, selectedGenre)
    }

    fun onRepeatButtonClicked() {
        requestDataFromServer()
    }

    private fun requestDataFromServer() {
        viewState.startContentLoading()

        filmModel.getFilms(object : FilmModel.GetFilmsCallback {

            override fun onSuccess(data: FilmResponse?) {
                films = data?.films ?: listOf()

                viewState.endContentLoading()
                viewState.showData(films, selectedGenre)
            }

            override fun onError(error: String) {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error)
            }
        })
    }
}