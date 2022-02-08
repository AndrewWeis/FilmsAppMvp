package com.example.filmsapp.mvp.views

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.filmsapp.ui.data.entities.Film
import com.example.filmsapp.ui.data.entities.GenreData

/**
 * Интерфейс для отображения списка фильмов
 */
interface FilmsView : ContentLoadingView {

    /**
     * Показать контент
     */
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showFilms(items: List<Film>, selectedGenre: GenreData?)
}