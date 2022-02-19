package com.example.filmsapp.mvp.models.base

/**
 * Callback с ошибкой
 */
interface ErrorCallback {
    /**
     * Срабатывает при ошибке
     *
     * @param error текст ошибки
     */
    fun onError(error: String)
}