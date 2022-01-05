package com.example.filmsapp.model.network

import com.example.filmsapp.model.network.response.FilmResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * A public interface that exposes the [getFilms] method
 */
interface FilmService {
    /**
     * Returns a Coroutine [List] of [FilmResponse] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "FilmResponse" endpoint will be requested with the GET HTTP method
     */
    @GET("films.json")
    fun getFilms() : Call<FilmResponse>
}