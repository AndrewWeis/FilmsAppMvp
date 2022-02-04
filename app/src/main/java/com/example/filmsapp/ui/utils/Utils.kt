package com.example.filmsapp.ui.utils

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