package com.example.filmsapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Класс для добавления хедеров
 */
class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
            it.addHeader("content-type", "application/json")
        }.build()

        return chain.proceed(request)
    }
}