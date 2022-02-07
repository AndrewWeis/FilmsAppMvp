package com.example.filmsapp.ui.fragments.base

import android.view.Window
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.filmsapp.ui.data.keyboard.KeyboardEventManager
import com.example.filmsapp.ui.data.keyboard.KeyboardVisibilityListener
import com.example.filmsapp.ui.data.screen_locker.ScreenLocker

/**
 * Базовый Fragment
 */
abstract class BaseFragment : MvpAppCompatFragment(), ScreenLocker {

    private val keyboardEventManager = KeyboardEventManager()

    override fun getScreenWindow(): Window? {
        return activity?.window
    }

    /**
     * Аналог метода [Activity.onBackPressed] для [androidx.fragment.app.Fragment]
     */
    protected open fun onBackPressed() {
        onBackPressed(true)
    }

    /**
     * Аналог метода [Activity.onBackPressed] для [androidx.fragment.app.Fragment]
     *
     * @param shouldHideKeyboard true - клавиатура будет скрыта, иначе остается в текущем состояние
     */
    protected open fun onBackPressed(shouldHideKeyboard: Boolean) {
        if (!shouldHideKeyboard) {
            return
        }

        hideKeyboard()
    }

    /**
     * Скрытие клавиатуры
     */
    protected fun hideKeyboard() {
        keyboardEventManager.hideKeyboard(activity)
    }

    /**
     * Показ клавиатуры
     */
    protected fun showKeyboard() {
        keyboardEventManager.showKeyboard(activity)
    }

    /**
     * Добавляет слушатель видимости клавиатуры
     *
     * @param listener [слушатель видимости клавиатуры][KeyboardVisibilityListener]
     */
    protected fun addKeyboardVisibilityListener(listener: KeyboardVisibilityListener) {
        keyboardEventManager.setKeyboardVisibilityListener(
            activity,
            viewLifecycleOwner,
            listener
        )
    }
}