package com.example.filmsapp.mvp.models

import com.example.filmsapp.data.network.NetworkCallback
import com.example.filmsapp.data.network.api.ApiFilms
import com.example.filmsapp.data.network.models.FilmResponse

/**
 * Реализация модели по получению фильмов с сервера
 */
class FilmModelProd(private var apiFilms: ApiFilms) : FilmModel {

    override fun getFilms(callback: FilmModel.GetFilmsCallback) {
        apiFilms.getFilms().enqueue(object : NetworkCallback<FilmResponse> {

            override fun onSuccess(response: FilmResponse?) {
                callback.onSuccess(response)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }
}
