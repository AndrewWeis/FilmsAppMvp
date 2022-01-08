package com.example.filmsapp.ui.films_list

import androidx.lifecycle.ViewModel
import com.example.filmsapp.model.FilmsListRVItem

class FilmsListViewModel: ViewModel() {

    var filmsListRVItems: List<FilmsListRVItem> = listOf()
    var films: List<FilmsListRVItem.Film> = listOf()
}