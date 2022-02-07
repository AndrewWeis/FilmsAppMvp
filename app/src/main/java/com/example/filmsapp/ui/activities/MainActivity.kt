package com.example.filmsapp.ui.activities

import android.os.Bundle
import com.example.filmsapp.R
import com.example.filmsapp.ui.activities.base.BaseActivity

/**
 * Главное activity
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}