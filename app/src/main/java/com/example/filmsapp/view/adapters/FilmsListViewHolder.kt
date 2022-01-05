package com.example.filmsapp.view.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding
import com.example.filmsapp.loadImage


sealed class FilmsListViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemTitleBinding): FilmsListViewHolder(binding) {
        fun bind(title: FilmsListRVItem.Title) {
            binding.title.text = title.title
        }
    }

    class FilmViewHolder(private val binding: ItemFilmBinding): FilmsListViewHolder(binding) {
        fun bind(film: FilmsListRVItem.Film) {
            binding.txFilmTitle.text = film.name ?: ""
            Log.i("ImageTest",film.id.toString())
            Log.i("ImageTest",film.imageUrl ?: "null")
            binding.previewImage.loadImage(film.imageUrl)
        }
    }

    class GenreViewHolder(private val binding: ItemGenreBinding): FilmsListViewHolder(binding) {
        fun bind(genre: FilmsListRVItem.Genre) {
            binding.genreBtn.text = genre.name
        }
    }
}