package com.example.filmsapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentFilmsListBinding
import com.example.filmsapp.view.FilmsListPresenter
import com.example.filmsapp.view.FilmsListView
import com.example.filmsapp.view.adapters.FilmsListAdapter
import com.example.filmsapp.view.adapters.FilmsListRVItem
import java.util.*

class FilmsListFragment : Fragment(R.layout.fragment_films_list), FilmsListView {

    private lateinit var presenter: FilmsListPresenter
    private lateinit var films: List<FilmsListRVItem.Film>

    private lateinit var binding: FragmentFilmsListBinding

    private lateinit var filmsListAdapter: FilmsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsListBinding.inflate(inflater)

        setUpRecyclerView()

        presenter = FilmsListPresenter(this)
        presenter.getFilms()



        return binding.root
    }

    private fun setUpRecyclerView() {
        filmsListAdapter = FilmsListAdapter()

        binding.filmsRV.apply {
            setHasFixedSize(true)

            val manager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

            manager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when(filmsListAdapter.items[position]) {
                        is FilmsListRVItem.Film -> 1
                        is FilmsListRVItem.Genre -> 1
                        is FilmsListRVItem.Title -> 2
                    }
                }
            }

            layoutManager = manager

            adapter = filmsListAdapter
        }
    }

    override fun showListFilms(list: List<FilmsListRVItem.Film>) {
        val filmsList = mutableListOf<FilmsListRVItem>()
        val genres: SortedSet<String> = sortedSetOf()

        filmsList.add(FilmsListRVItem.Title("Жанры"))

        list.forEach { film ->
            film.genres?.forEach { genre ->
                genres.add(genre)
            }
        }

        genres.forEach { filmsList.add(FilmsListRVItem.Genre(it)) }

        filmsList.add(FilmsListRVItem.Title("Фильмы"))

        filmsList.addAll(list)

        filmsListAdapter.items = filmsList
    }

    override fun showError(e: String) {
        Log.i("RetrofitCheck", "Failure: $e")
        Toast.makeText(requireContext(), "Check your internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }
}