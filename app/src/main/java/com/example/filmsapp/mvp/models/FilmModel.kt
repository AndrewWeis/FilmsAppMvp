package com.example.filmsapp.mvp.models

import com.example.filmsapp.data.network.models.FilmResponse
import com.example.filmsapp.mvp.models.base.BaseCallback

/**
 * Класс для получения данных с сервера.
 */
interface FilmModel {

    fun getFilms(callback: GetFilmsCallback)

    /**
     * Callback для передачи данных в presenter
     */
    interface GetFilmsCallback : BaseCallback<FilmResponse?>
}
