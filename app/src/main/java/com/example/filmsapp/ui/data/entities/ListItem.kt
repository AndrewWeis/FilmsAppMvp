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
    var leftMargin: Float? = null,
    var rightMargin: Float? = null,
    var topMargin: Float? = null,
    var bottomMargin: Float? = null
)