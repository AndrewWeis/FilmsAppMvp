package com.example.filmsapp.view

class FilmsListPresenter(
    private var view: FilmsListView?
) {

    fun getFilms() {

    }

    fun destroyView() {
        this.view = null
    }
}