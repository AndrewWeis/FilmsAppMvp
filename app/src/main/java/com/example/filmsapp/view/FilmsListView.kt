package com.example.filmsapp.view

import com.example.filmsapp.model.network.response.Film

interface FilmsListView {
    fun showListFilms(list: List<Film>)
    fun showError(e: String)
    fun showLoading()
    fun stopLoading()
}