package com.example.filmsapp.mvp.views

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.filmsapp.data.network.entities.Film

/**
 * Интерфейс для отображения списка фильмов
 */
interface FilmsView : ContentLoadingView {

    /**
     * Показать контент
     */
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showFilms(films: List<Film>, genres: List<String>, selectedGenreId: Int?)
}