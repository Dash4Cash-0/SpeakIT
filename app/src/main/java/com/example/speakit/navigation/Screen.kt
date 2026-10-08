package com.example.speakit.navigation

import androidx.annotation.StringRes
import com.example.speakit.R

sealed class Screen(val route: String, @StringRes val labelRes: Int) {
    object Home : Screen("home", R.string.nav_home)
    object Library : Screen("library", R.string.nav_library)
    object Settings : Screen("settings", R.string.nav_settings)
    object SentenceDetails : Screen("sentence/{sentenceId}", R.string.nav_sentence){
        fun createRoute(sentenceId: Long) = "sentence/$sentenceId"
    }
}
