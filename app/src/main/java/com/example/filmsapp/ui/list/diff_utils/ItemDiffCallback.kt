package com.example.filmsapp.ui.list.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.filmsapp.ui.list.models.FilmsListRVItem

/**
 * Класс для эффективного выявления разницы между двумя листами
 */
class ItemDiffCallback(
    private val oldList: List<FilmsListRVItem>,
    private val newList: List<FilmsListRVItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if (oldItem is FilmsListRVItem.Genre && newItem is FilmsListRVItem.Genre) {
            oldItem.name == newItem.name
        } else if (oldItem is FilmsListRVItem.Film && newItem is FilmsListRVItem.Film) {
            oldItem.id == newItem.id
        } else if (oldItem is FilmsListRVItem.Title && newItem is FilmsListRVItem.Title) {
            oldItem.title == newItem.title
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if (oldItem is FilmsListRVItem.Genre && newItem is FilmsListRVItem.Genre) {
            oldItem == newItem
        } else if (oldItem is FilmsListRVItem.Film && newItem is FilmsListRVItem.Film) {
            oldItem == newItem
        } else if (oldItem is FilmsListRVItem.Title && newItem is FilmsListRVItem.Title) {
            oldItem == newItem
        } else {
            false
        }
    }
}