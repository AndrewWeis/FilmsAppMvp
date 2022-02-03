package com.example.filmsapp.data.network.api

import com.example.filmsapp.data.network.models.FilmResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Api для получения фильмов с сервера
 */
interface ApiFilms {

    @GET("films.json")
    fun getFilms(): Call<FilmResponse>
}