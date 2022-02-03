package com.example.filmsapp.mvp.models.base

/**
 * Успешный Callback
 */
interface SuccessCallback<Type> {
    /**
     * Срабатывает при успехе
     *
     * @param data полученные данные
     */
    fun onSuccess(data: Type)
}