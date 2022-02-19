package com.example.filmsapp.mvp.views

import com.example.filmsapp.data.entities.Film
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

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