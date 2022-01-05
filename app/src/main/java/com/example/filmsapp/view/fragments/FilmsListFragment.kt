package com.example.filmsapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentFilmsListBinding
import com.example.filmsapp.model.network.response.Film
import com.example.filmsapp.view.FilmsListView

class FilmsListFragment : Fragment(R.layout.fragment_films_list), FilmsListView {

    private lateinit var presenter: FilmsListPresenter
    private lateinit var films: List<Film>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilmsListBinding.inflate(inflater)

        presenter = FilmsListPresenter(this)
        presenter.getFilms()


        return binding.root
    }

    override fun showListFilms(list: List<Film>) {
        Log.i("RetrofitCheck", list.toString())
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun stopLoading() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }
}