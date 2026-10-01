package com.example.speakit.navigation


sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object Library : Screen("library", "Library")
    object Settings : Screen("settings", "Settings")
}