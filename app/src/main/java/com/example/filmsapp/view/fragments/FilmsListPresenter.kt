package com.example.filmsapp.view.fragments

import com.example.filmsapp.model.network.ApiService
import com.example.filmsapp.model.network.response.FilmResponse
import com.example.filmsapp.view.FilmsListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListPresenter(
    private var view: FilmsListView?
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
                view?.showListFilms(response.body()?.films ?: listOf())
            }
        })
    }

    fun destroyView() {
        this.view = null
    }
}