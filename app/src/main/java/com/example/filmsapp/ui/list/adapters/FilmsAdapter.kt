package com.example.filmsapp.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.ui.data.entities.*
import com.example.filmsapp.ui.list.adapters.base.BaseSequenceAdapter
import com.example.filmsapp.ui.list.view_holders.FilmViewHolder
import com.example.filmsapp.ui.list.view_holders.FilmViewHolder.FilmViewHolderListener
import com.example.filmsapp.ui.list.view_holders.GenreViewHolder
import com.example.filmsapp.ui.list.view_holders.GenreViewHolder.GenreViewHolderListener
import com.example.filmsapp.ui.list.view_holders.HeaderViewHolder
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder

/**
 * Адаптер для отображения заголовков, фильмов, жанров
 */
class FilmsAdapter(
    layoutInflater: LayoutInflater,
    val genreViewHolderListener: GenreViewHolderListener,
    val filmViewHolderListener: FilmViewHolderListener
) :
    BaseSequenceAdapter<ListItem, BaseViewHolder>(layoutInflater) {

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
        return when (item.type) {
            ListItemTypes.FILMS_HEADER -> TYPE_FILMS_HEADER
            ListItemTypes.FILM -> TYPE_FILM
            ListItemTypes.GENRES_HEADER -> TYPE_GENRES_HEADER
            ListItemTypes.GENRE -> TYPE_GENRE
            else -> NOT_FOUND
        }
    }

    fun addListItems(listItems: List<ListItem>) {
        updateItems(listItems)
    }

    companion object {
        const val TYPE_FILMS_HEADER = 0
        const val TYPE_FILM = 1
        const val TYPE_GENRES_HEADER = 2
        const val TYPE_GENRE = 3
    }
}