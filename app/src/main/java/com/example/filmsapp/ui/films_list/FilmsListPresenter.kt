package com.example.filmsapp.ui.films_list

import com.example.filmsapp.model.FilmsListRVItem
import java.util.*

class FilmsListPresenter(
    private var view: FilmsListContract.View?,
    private val viewModel: FilmsListViewModel,
): FilmsListContract.Presenter, FilmsListContract.Model.OnFinishedListener {

    private val model: FilmsListContract.Model = FilmsListModel()

    override fun getFilteredFilms(genre: String) {
        prepareDataForRV(viewModel.films, genre)
        view?.setDataToRV(viewModel.filmsListRVItems)
    }

    override fun restoreItems(items: List<FilmsListRVItem>) {
        view?.showRestoredItems(items)
    }

    override fun onFinished(films: List<FilmsListRVItem.Film>) {
        viewModel.films = films

        prepareDataForRV(films, null)

        view?.setDataToRV(viewModel.filmsListRVItems)
        view?.stopLoading()
    }

    private fun prepareDataForRV(list: List<FilmsListRVItem.Film>, genre: String?) {
        val rvItems = mutableListOf<FilmsListRVItem>()
        val genres: SortedSet<String> = sortedSetOf()
        var sortedFilmsList = list.sortedBy { it.localName }

        rvItems.add(FilmsListRVItem.Title("Жанры"))

        sortedFilmsList.forEach { film ->
            film.genres?.forEach { genre ->
                genres.add(genre)
            }
        }

        genres.forEach {
            if (it == genre) {
                rvItems.add(FilmsListRVItem.Genre(it, true))
            } else {
                rvItems.add(FilmsListRVItem.Genre(it))
            }
        }

        rvItems.add(FilmsListRVItem.Title("Фильмы"))

        if (genre != null) {
            sortedFilmsList = sortedFilmsList.filter { it.genres?.contains(genre) == true }
        }

        rvItems.addAll(sortedFilmsList)

        viewModel.filmsListRVItems = rvItems.toList()
    }

    override fun onFailure(t: Throwable) {
        view?.onResponseFailure(t)
        view?.stopLoading()
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun requestDataFromServer() {
        view?.showLoading()
        model.getFilmsList(this)
    }
}