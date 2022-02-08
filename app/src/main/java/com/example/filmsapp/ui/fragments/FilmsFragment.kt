package com.example.filmsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FilmsFragmentBinding
import com.example.filmsapp.mvp.presenters.FilmsPresenter
import com.example.filmsapp.mvp.views.FilmsView
import com.example.filmsapp.ui.data.snackbar.MessagesHolder
import com.example.filmsapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.filmsapp.ui.list.adapters.FilmsAdapter
import com.example.filmsapp.ui.list.adapters.FilmsAdapter.Companion.TYPE_FILM
import com.example.filmsapp.ui.list.adapters.FilmsAdapter.Companion.TYPE_FILMS_HEADER
import com.example.filmsapp.ui.list.adapters.FilmsAdapter.Companion.TYPE_GENRE
import com.example.filmsapp.ui.list.adapters.FilmsAdapter.Companion.TYPE_GENRES_HEADER
import com.example.filmsapp.ui.list.adapters.ListExtension
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.list.entities.GenreData
import com.example.filmsapp.ui.list.generators.FilmsGenerator
import com.example.filmsapp.ui.list.view_holders.FilmViewHolder
import com.example.filmsapp.ui.list.view_holders.GenreViewHolder
import com.google.android.material.textview.MaterialTextView
import com.sequenia.app_bar_provider.AppBarSettings
import org.koin.android.ext.android.get

/**
 * Fragment для отображения списка фильмов
 */
class FilmsFragment :
    BaseWithAppBarNavigationFragment(),
    AppBarSettings,
    FilmsView,
    FilmViewHolder.FilmViewHolderListener,
    GenreViewHolder.GenreViewHolderListener {

    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private var listExtension: ListExtension? = null
    private val generator: FilmsGenerator = FilmsGenerator()
    private lateinit var adapter: FilmsAdapter
    private lateinit var messagesHolder: MessagesHolder

    @InjectPresenter
    lateinit var presenter: FilmsPresenter

    @ProvidePresenter
    fun provideFilmsPresenter(): FilmsPresenter {
        return FilmsPresenter(filmModel = get())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilmsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messagesHolder = MessagesHolder(viewLifecycleOwner, view)

        setUpToolBar()
        setUpAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showFilms(items: List<Film>, selectedGenre: GenreData?) {
        generator.generateList(items, selectedGenre)

        adapter.addListItems(
            generator.genresHeader,
            generator.genresList,
            generator.filmsHeader,
            generator.filmsList
        )
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

    override fun onFilmClick(film: Film) {
        val action = FilmsFragmentDirections.actionFilmsListToDetailedFilm(film)
        navigate(action)
    }

    override fun onGenreClick(genreData: GenreData) {
        presenter.onGenreClicked(genreData)
    }

    private fun setUpAdapter() {
        adapter = FilmsAdapter(layoutInflater)

        adapter.filmViewHolderListener = this
        adapter.genreViewHolderListener = this

        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    TYPE_FILMS_HEADER -> 2
                    TYPE_GENRES_HEADER -> 2
                    TYPE_GENRE -> 2
                    TYPE_FILM -> 1
                    else -> throw IllegalArgumentException("Unknown ViewHolder Type!")
                }
            }
        }

        listExtension = ListExtension(binding.filmsList)
        listExtension?.setLayoutManager(layoutManager)
        listExtension?.setAdapter(adapter)
    }

    private fun setUpToolBar() {
        appBarProvider?.setAppBarSettings(this)

        val rootLayout =
            appBarProvider?.setCustomToolbarView(R.layout.toolbar_centered_title_view) as ViewGroup

        rootLayout.findViewById<MaterialTextView>(R.id.toolbar_title_text).text =
            getString(R.string.title_films)
    }
}