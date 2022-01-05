package com.example.filmsapp.view.adapters

sealed class FilmsListRecyclerViewItem {

    class Title(
        val title: String
    ) : FilmsListRecyclerViewItem()

    class Film(
        val id: Long?,
        val localName: String?,
        val name: String?,
        val year: Int?,
        val rating: Double?,
        val imageUrl: String?,
        val description: String?,
        val genres: List<String>?
    ) : FilmsListRecyclerViewItem()

    class Genre(
        val name: String,
    ) : FilmsListRecyclerViewItem()

}