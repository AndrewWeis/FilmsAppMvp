package com.example.filmsapp.view.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBtnBinding
import com.example.filmsapp.databinding.ItemTitleBinding
import com.example.filmsapp.loadImage
import com.example.filmsapp.model.network.response.Film


sealed class FilmsListViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemTitleBinding): FilmsListViewHolder(binding) {
        fun bind(title: String) {
            binding.title.text = title
        }
    }

    class FilmViewHolder(private val binding: ItemFilmBinding): FilmsListViewHolder(binding) {
        fun bind(film: Film) {
            binding.txFilmTitle.text = film.name ?: ""
            binding.previewImage.loadImage(film.imageUrl)
        }
    }

    class GenreViewHolder(private val binding: ItemGenreBtnBinding): FilmsListViewHolder(binding) {
        fun bind(genre: String) {
            binding.genreBtn.text = genre
        }
    }
}