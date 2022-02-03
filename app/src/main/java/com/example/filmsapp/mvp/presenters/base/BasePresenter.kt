package com.example.filmsapp.mvp.presenters.base

import com.arellomobile.mvp.MvpPresenter
import com.example.filmsapp.mvp.views.base.BaseView

/**
 * Базовый presenter
 */
open class BasePresenter<View : BaseView> : MvpPresenter<View>()