package com.example.filmsapp.view.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding
import com.example.filmsapp.loadImage


sealed class FilmsListViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemTitleBinding): FilmsListViewHolder(binding) {
        fun bind(title: FilmsListRecyclerViewItem.Title) {
            binding.title.text = title.title
        }
    }

    class FilmViewHolder(private val binding: ItemFilmBinding): FilmsListViewHolder(binding) {
        fun bind(film: FilmsListRecyclerViewItem.Film) {
            binding.txFilmTitle.text = film.name ?: ""
            binding.previewImage.loadImage(film.imageUrl)
        }
    }

    class GenreViewHolder(private val binding: ItemGenreBinding): FilmsListViewHolder(binding) {
        fun bind(genre: FilmsListRecyclerViewItem.Genre) {
            binding.genreBtn.text = genre.name
        }
    }
}