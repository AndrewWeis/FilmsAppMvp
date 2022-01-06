package com.example.filmsapp.view

import com.example.filmsapp.model.network.ApiService
import com.example.filmsapp.model.network.response.FilmResponse
import com.example.filmsapp.view.adapters.FilmsListRVItem
import com.example.filmsapp.view.fragments.FilmsListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListPresenter(
    private var view: FilmsListView?,
    private val viewModel: FilmsListViewModel
) {

    fun getFilms() {
        view?.showLoading()

        ApiService.retrofitService.getFilms().enqueue( object: Callback<FilmResponse> {
            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                view?.stopLoading()
                view?.showError(t.message.toString())
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                view?.stopLoading()

                viewModel.films = response.body()?.films ?: listOf()
                view?.showListFilms(viewModel.films, null)
            }
        })
    }

    fun getFilteredFilms(genre: String) {
        view?.showListFilms(viewModel.films, genre)
    }

    fun restoreItems(items: List<FilmsListRVItem>) {
        view?.showRestoredItems(items)
    }

    fun destroyView() {
        this.view = null
    }
}