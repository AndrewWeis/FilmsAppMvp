package com.example.filmsapp.ui.films_list

import android.util.Log
import com.example.filmsapp.model.FilmResponse
import com.example.filmsapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListModel: FilmsListContract.Model {

    /**
     * This function will fetch films data
     * @param onFinishedListener
     */
    override fun getFilmsList(onFinishedListener: FilmsListContract.Model.OnFinishedListener) {
        ApiService.retrofitService.getFilms().enqueue( object: Callback<FilmResponse> {
            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e("Request failed", t.toString())
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                val films = response.body()?.films ?: listOf()
                onFinishedListener.onFinished(films)
            }
        })
    }
}