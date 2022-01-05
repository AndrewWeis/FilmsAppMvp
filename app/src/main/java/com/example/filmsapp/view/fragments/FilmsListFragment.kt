package com.example.filmsapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentFilmsListBinding
import com.example.filmsapp.model.network.ApiService
import com.example.filmsapp.model.network.response.FilmResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListFragment : Fragment(R.layout.fragment_films_list) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilmsListBinding.inflate(inflater)

        ApiService.retrofitService.getFilms().enqueue( object: Callback<FilmResponse> {
            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                //_response.value = "Failure: " + t.message
                Log.i("RetrofitCheck", "Failure: " + t.message)
            }

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                //_response.value = response.body()
                Log.i("RetrofitCheck", response.body().toString())
            }
        })

        return binding.root
    }

}