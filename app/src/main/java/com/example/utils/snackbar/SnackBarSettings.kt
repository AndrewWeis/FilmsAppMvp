package com.example.utils.snackbar

import androidx.annotation.ColorRes

/**
 * Класс для настройки SnackBar
 */
class SnackBarSettings(
    @ColorRes val buttonTextColor: Int?,
    @ColorRes val backgroundColor: Int?,
    val letterSpacing: Float?
) {
    constructor() : this(null, null, null)
}