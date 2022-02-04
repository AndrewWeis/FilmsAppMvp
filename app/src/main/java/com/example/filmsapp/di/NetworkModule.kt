package com.example.filmsapp.di

import com.example.filmsapp.data.network.NetworkManager
import com.example.filmsapp.data.network.api.ApiFilms
import org.koin.dsl.module

const val NETWORK_TIME_OUT = 60000L
const val API_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

val networkModule = module {
    single {
        NetworkManager(API_URL, NETWORK_TIME_OUT).createApiService(ApiFilms::class.java)
    }
}
