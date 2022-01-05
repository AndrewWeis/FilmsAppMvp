package com.example.filmsapp.view

import com.example.filmsapp.model.network.response.NetworkFilm

interface FilmsListView {
    fun showListFilms(list: List<NetworkFilm>)
    fun showError(e: String)
    fun showLoading()
    fun stopLoading()
}