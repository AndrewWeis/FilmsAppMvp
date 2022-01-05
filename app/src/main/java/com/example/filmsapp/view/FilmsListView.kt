package com.example.filmsapp.view

import com.example.filmsapp.view.adapters.FilmsListRVItem

interface FilmsListView {
    fun showListFilms(list: List<FilmsListRVItem.Film>)
    fun showError(e: String)
    fun showLoading()
    fun stopLoading()
}