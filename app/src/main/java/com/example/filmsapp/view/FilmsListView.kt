package com.example.filmsapp.view

import com.example.filmsapp.view.adapters.FilmsListRVItem

interface FilmsListView {
    fun showListFilms(list: List<FilmsListRVItem.Film>, genre: String?)
    fun showError(e: String)
    fun showLoading()
    fun stopLoading()
}