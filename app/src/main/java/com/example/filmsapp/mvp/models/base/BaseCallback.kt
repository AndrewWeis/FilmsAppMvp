package com.example.filmsapp.mvp.models.base

/**
 * Базовый Callback
 */
interface BaseCallback<Type> : ErrorCallback, SuccessCallback<Type> {
    override fun onSuccess(data: Type)
    override fun onError(error: String)
}