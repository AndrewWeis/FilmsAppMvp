package com.example.filmsapp.di

import com.example.filmsapp.mvp.presenters.FilmsPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory {
        FilmsPresenter(
            filmModel = get()
        )
    }
}