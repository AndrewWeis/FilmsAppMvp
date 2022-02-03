package com.example.filmsapp.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.example.filmsapp.R
import com.example.filmsapp.data.network.models.FilmResponse
import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.presenters.base.BasePresenter
import com.example.filmsapp.mvp.views.FilmsView
import com.example.filmsapp.ui.data.toDomain
import com.example.filmsapp.ui.list.models.FilmsListRVItem
import com.example.filmsapp.ui.utils.ResourcesUtils.getString
import com.example.filmsapp.ui.utils.firstCharToLowerCase
import com.example.filmsapp.ui.utils.firstCharToUpperCase
import java.util.*

/**
 * Presenter для работы со списком фильмов
 */
@InjectViewState
class FilmsPresenter(
    private val filmModel: FilmModel
) : BasePresenter<FilmsView>(), FilmModel.GetFilmsCallback {

    private var filmsListRVItems: List<FilmsListRVItem> = listOf()
    private var films: List<FilmsListRVItem.Film> = listOf()

    init {
        requestDataFromServer()
    }

    override fun onSuccess(data: FilmResponse?) {
        films = data?.films?.toDomain() ?: listOf()
        prepareDataForRV(data?.films?.toDomain() ?: listOf(), null)
        viewState.endContentLoading()
        viewState.showData(filmsListRVItems)
    }

    override fun onError(error: String) {
        viewState.endContentLoading()
        viewState.showContentLoadingError(error)
    }

    fun onGenreClicked(genre: String) {
        prepareDataForRV(films, genre)
        viewState.showData(filmsListRVItems)
    }

    fun onRepeatButtonClicked() {
        requestDataFromServer()
    }

    private fun requestDataFromServer() {
        viewState.startContentLoading()
        filmModel.getFilms(this)
    }

    /**
     * Преобразовывает лист фильмов полученных с сервера в другой лист
     * для отображения в recyclerView.
     */
    private fun prepareDataForRV(list: List<FilmsListRVItem.Film>, genre: String?) {

        val rvItems = mutableListOf<FilmsListRVItem>()
        val genres: SortedSet<String> = sortedSetOf()
        var sortedFilmsList = list.sortedBy { it.localName }

        rvItems.add(FilmsListRVItem.Title(getString(R.string.title_genres)))

        sortedFilmsList.forEach { film ->
            film.genres?.forEach { genre ->
                genres.add(genre.firstCharToUpperCase())
            }
        }

        genres.forEach {
            if (it == genre) {
                rvItems.add(FilmsListRVItem.Genre(it, true))
            } else {
                rvItems.add(FilmsListRVItem.Genre(it))
            }
        }

        rvItems.add(FilmsListRVItem.Title(getString(R.string.title_films)))

        if (genre != null) {
            sortedFilmsList = sortedFilmsList.filter {
                it.genres?.contains(genre.firstCharToLowerCase()) == true
            }
        }

        rvItems.addAll(sortedFilmsList)

        filmsListRVItems = rvItems.toList()
    }
}