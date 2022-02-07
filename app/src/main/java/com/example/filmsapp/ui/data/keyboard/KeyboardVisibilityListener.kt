package com.example.filmsapp.ui.data.keyboard

/**
 * Слушатель на видимость клавиатуры
 */
interface KeyboardVisibilityListener {

    /**
     * Срабатывает при показе клавиатуры
     */
    fun onKeyboardShow() {
    }

    /**
     * Срабатывает при скрытии клавиатуры
     */
    fun onKeyboardHide() {
    }
}