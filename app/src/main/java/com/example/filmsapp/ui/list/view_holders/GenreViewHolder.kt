package com.example.filmsapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.GenreItemBinding
import com.example.filmsapp.ui.data.entities.GenreData
import com.example.filmsapp.ui.data.entities.ListItem
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder

/**
 * View Holder для жанров
 */
open class GenreViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.genre_item) {

    private var binding: GenreItemBinding = GenreItemBinding.bind(itemView)
    private lateinit var genreData: GenreData
    private lateinit var listener: GenreViewHolderListener

    fun bind(listItem: ListItem, listener: GenreViewHolderListener) {
        this.genreData = listItem.data as GenreData
        this.listener = listener

        showGenreName()
        showSelectedGenre()

        setListener()
    }

    private fun showGenreName() {
        binding.genreText.text = genreData.genre
    }

    private fun showSelectedGenre() {
        binding.genreView.isSelected = genreData.isSelected
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick(genreData.id)
        }
    }

    /**
     * ClickListener для жанра
     */
    interface GenreViewHolderListener {
        fun onGenreClick(genreId: Int)
    }
}