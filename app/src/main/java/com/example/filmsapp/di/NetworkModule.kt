package com.example.filmsapp.di

import com.example.filmsapp.data.constants.NETWORK_TIME_OUT
import com.example.filmsapp.data.network.NetworkManager
import com.example.filmsapp.data.network.api.ApiFilms
import org.koin.dsl.module

val networkModule = module {

    val timeoutInMilliseconds: Long = NETWORK_TIME_OUT
    val apiUrl = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

    single {
        NetworkManager(apiUrl, timeoutInMilliseconds).createApiService(ApiFilms::class.java)
    }
}
