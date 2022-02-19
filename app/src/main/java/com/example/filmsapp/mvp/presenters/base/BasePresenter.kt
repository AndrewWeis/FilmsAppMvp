package com.example.filmsapp.mvp.presenters.base

import com.example.filmsapp.mvp.views.base.BaseView
import moxy.MvpPresenter

/**
 * Базовый presenter
 */
open class BasePresenter<View : BaseView> : MvpPresenter<View>()