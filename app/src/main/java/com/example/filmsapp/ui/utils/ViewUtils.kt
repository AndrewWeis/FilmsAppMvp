package com.example.filmsapp.ui.utils

import android.view.View
import android.view.ViewGroup
import com.example.utils.ResourcesUtils.getPxByDp

/**
 * Класс содержит функции помощники для работы с view
 */
object ViewUtils {

    /**
     * Выставляет margins для view
     */
    fun setMargins(view: View, left: Float?, right: Float?, top: Float?, bottom: Float?) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val params: ViewGroup.MarginLayoutParams =
                view.layoutParams as ViewGroup.MarginLayoutParams

            left?.let { params.leftMargin = getPxByDp(it) }
            right?.let { params.rightMargin = getPxByDp(it) }
            top?.let { params.topMargin = getPxByDp(it) }
            bottom?.let { params.bottomMargin = getPxByDp(it) }

            view.requestLayout()
        }
    }
}