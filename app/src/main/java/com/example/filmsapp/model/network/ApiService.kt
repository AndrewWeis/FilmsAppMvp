package com.example.filmsapp.model.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

/**
 * Build the Moshi object that Retrofit will be using
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(getRetrofitClient())
    .baseUrl(BASE_URL)
    .build()

/**
 * Helper function to add logging
 */
private fun getRetrofitClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
                it.addHeader("content-type", "application/json")
            }.build())
        }.also { client ->
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build()

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ApiService {
    val retrofitService : FilmService by lazy {
        retrofit.create(FilmService::class.java)
    }
}