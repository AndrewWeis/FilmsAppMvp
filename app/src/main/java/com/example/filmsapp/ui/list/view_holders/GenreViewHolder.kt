package com.example.filmsapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.GenreItemBinding
import com.example.filmsapp.ui.data.entities.GenreData
import com.example.filmsapp.ui.data.entities.ListItem
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder
import com.example.utils.ResourcesUtils.getColor

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
        if (genreData.isSelected) {
            binding.genreLayout.setBackgroundColor(getColor(R.color.teal_D7F6F8))
        } else {
            binding.genreLayout.setBackgroundColor(getColor(R.color.white_FFFFFF))
        }
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick(genreData)
        }
    }

    /**
     * ClickListener для жанра
     */
    interface GenreViewHolderListener {
        fun onGenreClick(genreData: GenreData)
    }
}