package com.example.filmsapp.ui.utils

import android.view.View
import android.view.ViewGroup
import com.example.utils.ResourcesUtils.getPxByDp

/**
 * Выставляет margins для view
 */
fun setMargins(view: View, left: Float, right: Float, top: Float, bottom: Float) {
    if (view.layoutParams is ViewGroup.MarginLayoutParams) {
        val params: ViewGroup.MarginLayoutParams =
            view.layoutParams as ViewGroup.MarginLayoutParams

        params.setMargins(
            getPxByDp(left),
            getPxByDp(top),
            getPxByDp(right),
            getPxByDp(bottom)
        )

        view.requestLayout()
    }
}