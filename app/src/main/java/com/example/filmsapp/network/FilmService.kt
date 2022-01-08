package com.example.filmsapp.network

import com.example.filmsapp.model.FilmResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * A public interface that exposes the [getFilms] method
 */
interface FilmService {

    @GET("films.json")
    fun getFilms() : Call<FilmResponse>
}