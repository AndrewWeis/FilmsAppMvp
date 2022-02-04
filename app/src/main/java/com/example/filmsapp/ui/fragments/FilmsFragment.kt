package com.example.filmsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.filmsapp.databinding.FilmsFragmentBinding
import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.presenters.FilmsPresenter
import com.example.filmsapp.mvp.views.FilmsView
import com.example.filmsapp.ui.data.snackbar.MessagesHolder
import com.example.filmsapp.ui.list.adapters.FilmsListAdapter
import com.example.filmsapp.ui.list.models.FilmsListRVItem
import com.example.filmsapp.ui.list.view_holders.FilmsListViewHolder
import org.koin.android.ext.android.get

/**
 * View для отображения списка фильмов
 */
class FilmsFragment :
    MvpAppCompatFragment(),
    FilmsView,
    FilmsListViewHolder.FilmViewHolderListener {

    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var filmsListAdapter: FilmsListAdapter
    private lateinit var messagesHolder: MessagesHolder

    @InjectPresenter
    lateinit var presenter: FilmsPresenter

    @ProvidePresenter
    fun provideFilmsPresenter(): FilmsPresenter {
        return FilmsPresenter(filmModel = get<FilmModel>())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        _binding = FilmsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        messagesHolder = MessagesHolder(viewLifecycleOwner, layoutInflater, binding.root)

        setUpRecyclerView()
        setUpToolBar()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showData(items: List<FilmsListRVItem>) {
        filmsListAdapter.items = items
    }

    override fun startContentLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun endContentLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showContentLoadingError(error: String) {
        messagesHolder.showUnhiddenNetworkError(error) { presenter.onRepeatButtonClicked() }
    }

    override fun onFilmClick(item: FilmsListRVItem) {
        when (item) {
            is FilmsListRVItem.Film -> {
                val action = FilmsFragmentDirections
                    .actionFilmsListFragmentToDetailedFilmFragment(
                        item,
                        item.name ?: ""
                    )
                findNavController().navigate(action)
            }
            is FilmsListRVItem.Genre -> {
                presenter.onGenreClicked(item.name)
            }
            is FilmsListRVItem.Title -> {
                // skip
            }
        }
    }

    private fun setUpRecyclerView() {
        filmsListAdapter = FilmsListAdapter(this)

        binding.filmsRV.apply {
            setHasFixedSize(true)

            val manager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (filmsListAdapter.items[position]) {
                        is FilmsListRVItem.Film -> 1
                        is FilmsListRVItem.Genre -> 2
                        is FilmsListRVItem.Title -> 2
                    }
                }
            }

            layoutManager = manager
            adapter = filmsListAdapter
        }
    }

    private fun setUpToolBar() {
        binding.toolbar.title = ""

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}