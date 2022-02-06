package com.example.filmsapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FilmItemBinding
import com.example.filmsapp.ui.data.image_loader.ImageLoader
import com.example.filmsapp.ui.list.Film
import com.example.filmsapp.ui.list.ListItem
import com.example.filmsapp.ui.list.Settings
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder
import com.example.filmsapp.ui.utils.ResourcesUtils
import com.example.filmsapp.ui.utils.setMargins

/**
 * View Holder для фильмов
 */
open class FilmViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.film_item) {

    private var binding: FilmItemBinding = FilmItemBinding.bind(itemView)
    private lateinit var settings: Settings
    private lateinit var film: Film
    private lateinit var listener: FilmViewHolderListener

    fun bind(listItem: ListItem, listener: FilmViewHolderListener) {
        this.film = listItem.data as Film
        this.settings = listItem.settings
        this.listener = listener

        showFilmName()
        showFilmPreview()

        setFilmMargins()
        setListener()
    }

    private fun showFilmName() {
        binding.filmTitleText.text = film.name
    }

    private fun showFilmPreview() {
        ImageLoader
            .load(film.imageUrl)
            .error(R.drawable.img_not_found)
            .placeholder(R.drawable.img_not_found)
            .centerCrop()
            .roundedCorners(ResourcesUtils.getPxByDp(4f))
            .into(binding.previewImage)
    }

    private fun setFilmMargins() {
        setMargins(
            binding.filmLayout,
            settings.leftMargin,
            settings.rightMargin,
            settings.topMargin,
            settings.bottomMargin
        )
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onFilmClick(film)
        }
    }

    /**
     * ClickListener для фильма
     */
    interface FilmViewHolderListener {
        fun onFilmClick(film: Film)
    }
}