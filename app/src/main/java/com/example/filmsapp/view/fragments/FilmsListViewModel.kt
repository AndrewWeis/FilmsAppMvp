package com.example.filmsapp.view.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsapp.view.adapters.FilmsListRVItem

class FilmsListViewModel: ViewModel() {

    private val _filmsListRVItems = MutableLiveData<List<FilmsListRVItem>>()
    val filmsListRVItems: LiveData<List<FilmsListRVItem>>
        get() = _filmsListRVItems

    fun initFilmsListRVItems(films:List<FilmsListRVItem>) {
        _filmsListRVItems.value = films
    }

    var films: List<FilmsListRVItem.Film> = listOf()
}