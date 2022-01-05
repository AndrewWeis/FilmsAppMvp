package com.example.filmsapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding

class FilmsListAdapter: RecyclerView.Adapter<FilmsListViewHolder>() {

    var items = listOf<FilmsListRecyclerViewItem>()
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
        when(holder) {
            is FilmsListViewHolder.FilmViewHolder -> holder.bind(items[position] as FilmsListRecyclerViewItem.Film)
            is FilmsListViewHolder.GenreViewHolder -> holder.bind(items[position] as FilmsListRecyclerViewItem.Genre)
            is FilmsListViewHolder.TitleViewHolder -> holder.bind(items[position] as FilmsListRecyclerViewItem.Title)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is FilmsListRecyclerViewItem.Film -> R.layout.item_film
            is FilmsListRecyclerViewItem.Genre -> R.layout.item_genre
            is FilmsListRecyclerViewItem.Title -> R.layout.item_title
        }
    }
}