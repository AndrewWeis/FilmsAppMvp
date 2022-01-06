package com.example.filmsapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding

class FilmsListAdapter(val onClickListener: OnClickListener?): RecyclerView.Adapter<FilmsListViewHolder>() {

    var items = listOf<FilmsListRVItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsListViewHolder {
        return when(viewType) {
            R.layout.item_title -> FilmsListViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_film -> FilmsListViewHolder.FilmViewHolder(
                ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_genre -> FilmsListViewHolder.GenreViewHolder(
                ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: FilmsListViewHolder, position: Int) {
        holder.onClickListener = onClickListener
        when(holder) {
            is FilmsListViewHolder.FilmViewHolder -> holder.bind(items[position] as FilmsListRVItem.Film)
            is FilmsListViewHolder.GenreViewHolder -> holder.bind(items[position] as FilmsListRVItem.Genre)
            is FilmsListViewHolder.TitleViewHolder -> holder.bind(items[position] as FilmsListRVItem.Title)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is FilmsListRVItem.Film -> R.layout.item_film
            is FilmsListRVItem.Genre -> R.layout.item_genre
            is FilmsListRVItem.Title -> R.layout.item_title
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [FilmsListRVItem]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [FilmsListRVItem]
     */
    class OnClickListener(val clickListener: (item: FilmsListRVItem) -> Unit) {
        fun onClick(item: FilmsListRVItem) = clickListener(item)
    }
}