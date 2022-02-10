package com.example.filmsapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filmsapp.R
import com.example.filmsapp.databinding.HeaderItemBinding
import com.example.filmsapp.ui.data.entities.HeaderData
import com.example.filmsapp.ui.data.entities.ListItem
import com.example.filmsapp.ui.data.entities.Settings
import com.example.filmsapp.ui.list.view_holders.base.BaseViewHolder
import com.example.filmsapp.ui.utils.ViewUtils

/**
 * View Holder для заголовков
 */
class HeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {

    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var header: HeaderData
    private lateinit var settings: Settings

    fun bind(listItem: ListItem) {
        this.header = listItem.data as HeaderData
        this.settings = listItem.settings

        showHeaderName()

        setHeaderMargins()
    }

    private fun showHeaderName() {
        binding.titleText.text = header.title
    }

    private fun setHeaderMargins() {
        ViewUtils.setMargins(
            binding.headerView,
            settings.leftMargin,
            settings.rightMargin,
            settings.topMargin,
            settings.bottomMargin
        )
    }
}