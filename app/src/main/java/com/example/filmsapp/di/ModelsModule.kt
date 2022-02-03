package com.example.filmsapp.di

import com.example.filmsapp.mvp.models.FilmModel
import com.example.filmsapp.mvp.models.FilmModelProd
import org.koin.dsl.module

val modelsModule = module {
    factory<FilmModel> {
        FilmModelProd(
            apiFilms = get()
        )
    }
}
