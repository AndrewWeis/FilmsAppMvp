package com.example.filmsapp.ui.utils

import android.view.View
import android.view.ViewGroup
import com.example.filmsapp.ui.utils.ResourcesUtils.getPxByDp

/**
 * Переводит первую букву строки в заглавную
 */
fun String.firstCharToUpperCase(): String {
    return replaceFirstChar { it.uppercase() }
}

/**
 * Переводит первую букву строки в маленькую
 */
fun String.firstCharToLowerCase(): String {
    return replaceFirstChar { it.lowercase() }
}

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