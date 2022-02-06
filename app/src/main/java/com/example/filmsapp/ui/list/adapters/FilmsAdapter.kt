package com.example.filmsapp.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.ui.list.*
import com.example.filmsapp.ui.list.adapters.base.BaseSequenceAdapter
import com.example.filmsapp.ui.list.view_holders.FilmViewHolder
import com.example.filmsapp.ui.list.view_holders.GenreViewHolder
import com.example.filmsapp.ui.list.view_holders.HeaderViewHolder
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder

class FilmsAdapter(layoutInflater: LayoutInflater) :
    BaseSequenceAdapter<ListItem, BaseViewHolder>(layoutInflater) {

    lateinit var genreViewHolderListener: GenreViewHolder.GenreViewHolderListener
    lateinit var filmViewHolderListener: FilmViewHolder.FilmViewHolderListener

    override fun onBindViewHolder(holder: BaseViewHolder, item: ListItem) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(item)
            is GenreViewHolder -> holder.bind(item, genreViewHolderListener)
            is FilmViewHolder -> holder.bind(item, filmViewHolderListener)
        }
    }

    override fun getTypeSequence() = intArrayOf(
        TYPE_GENRES_HEADER,
        TYPE_GENRE,
        TYPE_FILMS_HEADER,
        TYPE_FILM
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_GENRES_HEADER, TYPE_FILMS_HEADER -> HeaderViewHolder(layoutInflater, parent)
            TYPE_GENRE -> GenreViewHolder(layoutInflater, parent)
            TYPE_FILM -> FilmViewHolder(layoutInflater, parent)
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override fun getItemViewType(item: ListItem): Int {
        return when (item.data) {
            is FilmHeader -> TYPE_FILMS_HEADER
            is Film -> TYPE_FILM
            is GenreHeader -> TYPE_GENRES_HEADER
            is Genre -> TYPE_GENRE
            else -> NOT_FOUND
        }
    }

    fun addListItems(
        genresHeader: ListItem,
        genres: List<ListItem>,
        filmsHeader: ListItem,
        films: List<ListItem>
    ) {
        updateItem(genresHeader, TYPE_GENRES_HEADER)
        updateItems(genres, TYPE_GENRE)
        updateItem(filmsHeader, TYPE_FILMS_HEADER)
        updateItems(films, TYPE_FILM)
    }

    companion object {
        const val TYPE_FILMS_HEADER = 0
        const val TYPE_FILM = 1
        const val TYPE_GENRES_HEADER = 2
        const val TYPE_GENRE = 3
    }
}