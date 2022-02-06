package com.example.filmsapp.mvp.views

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.list.entities.GenreData

/**
 * Интерфейс для отображения списка фильмов
 */
interface FilmsView : ContentLoadingView {

    /**
     * Показать контент
     */
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showData(items: List<Film>, selectedGenre: GenreData?)
}