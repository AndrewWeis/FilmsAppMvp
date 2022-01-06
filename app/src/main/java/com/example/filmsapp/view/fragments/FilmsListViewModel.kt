package com.example.filmsapp.view.fragments

import androidx.lifecycle.ViewModel
import com.example.filmsapp.view.adapters.FilmsListRVItem

class FilmsListViewModel: ViewModel() {

    var filmsListRVItems: List<FilmsListRVItem> = listOf()
    var films: List<FilmsListRVItem.Film> = listOf()
}