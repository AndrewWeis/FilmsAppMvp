package com.example.filmsapp.ui.utils

import android.widget.ImageView
import com.example.filmsapp.R
import com.example.filmsapp.data.constants.FILM_CORNERS_RADIUS
import com.example.filmsapp.ui.data.image_loader.ImageLoader
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

/**
 * Загружает превью картинку фильма
 */
fun loadFilmPreview(url: String?, view: ImageView) {
    ImageLoader
        .load(url)
        .error(R.drawable.img_not_found)
        .placeholder(R.drawable.img_not_found)
        .centerCrop()
        .roundedCorners(getPxByDp(FILM_CORNERS_RADIUS))
        .into(view)
}