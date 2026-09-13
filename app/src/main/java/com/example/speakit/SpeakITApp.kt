package com.example.speakit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.navigation.Screen
import com.example.speakit.screens.Home
import com.example.speakit.screens.Library
import com.example.speakit.screens.Settings

@Composable
fun SpeakITApp() {
    val navController = rememberNavController()
    //val brush = Brush.verticalGradient(listOf(Color(0xFF389C27), Color(0xFFD5E6DF)))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavbar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .drawBehind{
                    drawRect(Color(0xFFf3f2f2))
                }
        ){
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) { Home() }
                composable(Screen.Library.route) { Library() }
                composable(Screen.Settings.route) { Settings() }
            }
        }
    }
}