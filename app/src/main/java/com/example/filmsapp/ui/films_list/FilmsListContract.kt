package com.example.filmsapp.ui.films_list

import com.example.filmsapp.model.FilmsListRVItem

interface FilmsListContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(films: List<FilmsListRVItem.Film>)
            fun onFailure(t: Throwable)
        }

        fun getFilmsList(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun showRestoredItems(items: List<FilmsListRVItem>)
        fun setDataToRV(list: List<FilmsListRVItem>)
        fun onResponseFailure(t: Throwable)
        fun showLoading()
        fun stopLoading()
    }

    interface Presenter {
        fun onDestroy()
        fun requestDataFromServer()
        fun getFilteredFilms(genre: String)
        fun restoreItems(items: List<FilmsListRVItem>)
    }
}