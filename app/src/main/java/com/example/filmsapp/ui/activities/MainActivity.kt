package com.example.filmsapp.ui.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.example.filmsapp.R
import com.example.filmsapp.ui.activities.base.BaseActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.sequenia.app_bar_provider.AppBarProvider
import com.sequenia.app_bar_provider.AppBarProviderImp
import com.sequenia.app_bar_provider.AppBarViews

/**
 * Главное activity
 */
class MainActivity : BaseActivity(), AppBarViews, AppBarProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun getAppBar(): AppBarLayout {
        return findViewById(R.id.app_bar)
    }

    override fun getToolbar(): Toolbar {
        return findViewById(R.id.toolbar)
    }

    override fun getCollapsingContent(): ViewGroup {
        return findViewById(R.id.collapsing_content) as ViewGroup
    }

    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout {
        return findViewById(R.id.collapsing_toolbar)
    }

    override fun getAppBarProviderImp(): AppBarProviderImp {
        return AppBarProviderImp(this)
    }
}