package com.example.filmsapp.mvp.views

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.filmsapp.mvp.views.base.BaseView

/**
 * Интерфейс для отображения состояний загрузки / ошибки
 */
interface ContentLoadingView : BaseView {

    /**
     * Начата загрузка контента
     *
     * Очищает весь ViewState
     */
    @StateStrategyType(value = SingleStateStrategy::class)
    fun startContentLoading()

    /**
     * Загрузка контента завершена
     */
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun endContentLoading()

    /**
     * Показать ошибку контента
     *
     * @param error текст ошибки
     */
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showContentLoadingError(error: String)
}