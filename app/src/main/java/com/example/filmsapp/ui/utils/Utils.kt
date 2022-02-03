package com.example.filmsapp.ui.utils

import android.widget.ImageView
import com.example.filmsapp.R
import com.example.filmsapp.data.constants.FILM_CORNERS_RADIUS
import com.example.filmsapp.ui.data.image_loader.ImageLoader
import com.example.filmsapp.ui.utils.ResourcesUtils.getPxByDp

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

/**
 * Форматирует текст длины length, учитывая свисающие знаки пунктуации
 *
 * @param length - кол-во символов
 */
fun String.smartTruncate(length: Int): String {
    val words = split(" ")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()
    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore) {
        builder.append("...")
    }
    return builder.toString()
}

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