package com.example.filmsapp.mvp.views

import android.view.View

/**
 * Интерфейс для управления состоянием загрузки контента на view
 */
interface ContentLoading : ContentLoadingView {

    fun getContentView(): View? = null
    fun getContentLoadingView(): View? = null

    override fun startContentLoading() {
        setContentViewVisibility(false)
        setContentLoadingViewVisibility(true)
    }

    override fun endContentLoading() {
        setContentViewVisibility(true)
        setContentLoadingViewVisibility(false)
    }

    override fun showContentLoadingError(error: String) {
        // перекрыть, если надо показать ошибку загрузки контента
    }

    private fun setContentViewVisibility(isVisible: Boolean) {
        getContentView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setContentLoadingViewVisibility(isVisible: Boolean) {
        getContentLoadingView()?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}