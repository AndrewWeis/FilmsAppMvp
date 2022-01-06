package com.example.filmsapp.view

import com.example.filmsapp.model.network.ApiService
import com.example.filmsapp.model.network.response.FilmResponse
import com.example.filmsapp.view.adapters.FilmsListRVItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListPresenter(
    private var view: FilmsListView?
) {

    lateinit var films: List<FilmsListRVItem.Film>

    fun getFilms() {
        view?.showLoading()

        ApiService.retrofitService.getFilms().enqueue( object: Callback<FilmResponse> {
            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                view?.stopLoading()
                view?.showError(t.message.toString())
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                view?.stopLoading()
                films = response.body()?.films ?: listOf()
                view?.showListFilms(films, null)
            }
        })
    }

    fun getFilteredFilms(genre: String) {
        view?.showListFilms(films, genre)
    }

    fun destroyView() {
        this.view = null
    }
}