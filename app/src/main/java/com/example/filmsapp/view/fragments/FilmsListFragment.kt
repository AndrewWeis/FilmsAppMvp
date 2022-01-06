package com.example.filmsapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentFilmsListBinding
import com.example.filmsapp.view.FilmsListPresenter
import com.example.filmsapp.view.FilmsListView
import com.example.filmsapp.view.adapters.FilmsListAdapter
import com.example.filmsapp.view.adapters.FilmsListRVItem
import com.google.android.material.snackbar.Snackbar
import java.util.*


class FilmsListFragment : Fragment(R.layout.fragment_films_list), FilmsListView {

    private lateinit var presenter: FilmsListPresenter
    private lateinit var binding: FragmentFilmsListBinding
    private lateinit var filmsListAdapter: FilmsListAdapter

    private val viewModel by viewModels<FilmsListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsListBinding.inflate(inflater)

        setUpRecyclerView()

        presenter = FilmsListPresenter(this, viewModel)

        /**
         * Case 1. When a fragment is created for the first time, we load data from a remote service
         * Case 2. When a fragment was overlapped by another fragment
         * Case 3. When a fragment is completely recreated, for example, when changing orientation
         */
        if (savedInstanceState == null) {
            if (viewModel.filmsListRVItems.isNotEmpty()) {
                presenter.restoreItems(viewModel.filmsListRVItems)
            } else {
                presenter.getFilms()
            }
        } else {
            presenter.restoreItems(viewModel.filmsListRVItems)
        }


        return binding.root
    }

    private fun setUpRecyclerView() {
        filmsListAdapter = FilmsListAdapter(FilmsListAdapter.OnClickListener {
            when(it) {
                is FilmsListRVItem.Film -> {
                    val action = FilmsListFragmentDirections.actionFilmsListFragmentToDetailedFilmFragment(it, it.localName ?: "Title is lost :c")
                    findNavController().navigate(action)
                }
                is FilmsListRVItem.Genre -> {
                    presenter.getFilteredFilms(it.name)
                }
                is FilmsListRVItem.Title -> {
                   /* do nothing */
                }
            }
        })


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

    override fun showRestoredItems(items: List<FilmsListRVItem>) {
        filmsListAdapter.items = items
    }

    override fun showListFilms(list: List<FilmsListRVItem.Film>, genre: String?) {
        val rvItems = mutableListOf<FilmsListRVItem>()
        val genres: SortedSet<String> = sortedSetOf()
        var sortedFilmsList = list.sortedBy { it.localName }

        rvItems.add(FilmsListRVItem.Title("Жанры"))

        sortedFilmsList.forEach { film ->
            film.genres?.forEach { genre ->
                genres.add(genre)
            }
        }

        genres.forEach {
            if (it == genre) {
                rvItems.add(FilmsListRVItem.Genre(it, true))
            } else {
                rvItems.add(FilmsListRVItem.Genre(it))
            }
        }

        rvItems.add(FilmsListRVItem.Title("Фильмы"))

        if (genre != null) {
            sortedFilmsList = sortedFilmsList.filter { it.genres?.contains(genre) == true }
        }

        rvItems.addAll(sortedFilmsList)

        viewModel.filmsListRVItems = rvItems.toList()

        filmsListAdapter.items = rvItems
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