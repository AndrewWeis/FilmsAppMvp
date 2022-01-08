package com.example.filmsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding
import com.example.filmsapp.model.FilmsListRVItem


sealed class FilmsListViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    var onClickListener: FilmsListAdapter.OnClickListener? = null

    class TitleViewHolder(private val binding: ItemTitleBinding): FilmsListViewHolder(binding) {
        fun bind(title: FilmsListRVItem.Title) {
            binding.title = title
        }
    }

    class FilmViewHolder(private val binding: ItemFilmBinding): FilmsListViewHolder(binding) {
        fun bind(film: FilmsListRVItem.Film) {
            binding.film = film
            binding.filmClick = onClickListener
        }
    }

    class GenreViewHolder(private val binding: ItemGenreBinding): FilmsListViewHolder(binding) {
        fun bind(genre: FilmsListRVItem.Genre) {
            binding.genre = genre
            binding.genreClick = onClickListener
        }
    }
}