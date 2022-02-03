package com.example.filmsapp.ui.list.view_holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.filmsapp.R
import com.example.filmsapp.databinding.ItemFilmBinding
import com.example.filmsapp.databinding.ItemGenreBinding
import com.example.filmsapp.databinding.ItemTitleBinding
import com.example.filmsapp.ui.list.models.FilmsListRVItem
import com.example.filmsapp.ui.utils.ResourcesUtils.getColor
import com.example.filmsapp.ui.utils.loadFilmPreview

/**
 * ViewHolder для элементов списка
 */
sealed class FilmsListViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var listener: FilmViewHolderListener

    /**
     * ViewHolder для заголовков
     */
    class TitleViewHolder(private val binding: ItemTitleBinding) : FilmsListViewHolder(binding) {
        fun bind(title: FilmsListRVItem.Title) {
            binding.titleText.text = title.title

            binding.titleLayout.setOnClickListener {
                listener.onFilmClick(title)
            }
        }
    }

    /**
     * ViewHolder для фильмов
     */
    class FilmViewHolder(private val binding: ItemFilmBinding) : FilmsListViewHolder(binding) {
        fun bind(film: FilmsListRVItem.Film) {
            binding.filmTitleText.text = film.shortTitle

            binding.filmLayout.setOnClickListener {
                listener.onFilmClick(film)
            }

            loadFilmPreview(film.imageUrl, binding.previewImage)
        }
    }

    /**
     * ViewHolder для жанров
     */
    class GenreViewHolder(private val binding: ItemGenreBinding) : FilmsListViewHolder(binding) {
        fun bind(genre: FilmsListRVItem.Genre) {
            binding.genreText.text = genre.name

            binding.genreLayout.setOnClickListener {
                listener.onFilmClick(genre)
            }

            selectGenre(genre.isSelected)
        }

        private fun selectGenre(isSelected: Boolean) {
            if (isSelected) {
                binding.genreLayout.setBackgroundColor(getColor(R.color.teal_D7F6F8))
            } else {
                binding.genreLayout.setBackgroundColor(getColor(R.color.white))
            }
        }
    }

    /**
     * Listener, обрабатывающий клики на элементы листа.
     */
    interface FilmViewHolderListener {
        fun onFilmClick(item: FilmsListRVItem)
    }
}