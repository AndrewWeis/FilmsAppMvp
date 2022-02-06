package com.example.filmsapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.GenreItemBinding
import com.example.filmsapp.ui.list.Genre
import com.example.filmsapp.ui.list.ListItem
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder
import com.example.filmsapp.ui.utils.ResourcesUtils

/**
 * View Holder для жанров
 */
open class GenreViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.genre_item) {

    private var binding: GenreItemBinding = GenreItemBinding.bind(itemView)
    private lateinit var genre: Genre
    private lateinit var listener: GenreViewHolderListener

    fun bind(listItem: ListItem, listener: GenreViewHolderListener) {
        this.genre = listItem.data as Genre
        this.listener = listener

        showGenreName()
        showSelectedGenre()

        setListener()
    }

    private fun showGenreName() {
        binding.genreText.text = genre.genre
    }

    private fun showSelectedGenre() {
        if (genre.isSelected) {
            binding.genreLayout.setBackgroundColor(ResourcesUtils.getColor(R.color.teal_D7F6F8))
        } else {
            binding.genreLayout.setBackgroundColor(ResourcesUtils.getColor(R.color.white))
        }
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick(genre)
        }
    }

    /**
     * ClickListener для жанра
     */
    interface GenreViewHolderListener {
        fun onGenreClick(genre: Genre)
    }
}