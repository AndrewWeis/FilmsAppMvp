package com.example.filmsapp.ui.films_list

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
import com.example.filmsapp.adapters.FilmsListAdapter
import com.example.filmsapp.model.FilmsListRVItem
import java.util.*


class FilmsListFragment : Fragment(R.layout.fragment_films_list), FilmsListContract.View {

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
                presenter.requestDataFromServer()
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

    override fun setDataToRV(list: List<FilmsListRVItem>) {
        filmsListAdapter.items = list
    }

    override fun onResponseFailure(t: Throwable) {
        Log.i("RetrofitCheck", "Failure: ${t.message}")
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
        presenter.onDestroy()
    }
}