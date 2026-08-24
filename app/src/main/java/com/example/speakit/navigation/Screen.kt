package com.example.speakit.navigation

import androidx.annotation.DrawableRes
import com.example.speakit.R

sealed class Screen(val route: String, val label: String, @param:DrawableRes val iconRes: Int) {
    object Home : Screen("home", "Home", R.drawable.home_color)
    object Library : Screen("library", "Library", R.drawable.library_color)
    object Settings : Screen("settings", "Settings", R.drawable.settings_color)
}