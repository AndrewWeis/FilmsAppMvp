package com.example.filmsapp.ui.activities

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.filmsapp.R
import com.example.filmsapp.ui.activities.base.BaseActivity

/**
 * Главное activity. Используется паттерн single activity
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}