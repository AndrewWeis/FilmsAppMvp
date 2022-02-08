package com.example.filmsapp.ui.data.entities

/**
 * Универсальный объект отображения
 */
data class ListItem(
    var type: ListItemTypes? = null,
    var id: String? = null,
    var data: Any,
    var settings: Settings = Settings(),
    var errors: Errors = Errors()
)

/**
 * Ошибка объекта отображения
 */
class Errors(var message: String?) {
    constructor() : this(null)
}

/**
 * Настройки объекта отображения
 */
class Settings(
    var leftMargin: Float,
    var rightMargin: Float,
    var topMargin: Float,
    var bottomMargin: Float
) {
    constructor() : this(0f, 0f, 0f, 0f)
}