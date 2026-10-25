package com.example.speakit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.navigation.Screen
import com.example.speakit.screens.Home
import com.example.speakit.screens.Library
import com.example.speakit.screens.Login
import com.example.speakit.screens.Register
import com.example.speakit.screens.SentenceDetails
import com.example.speakit.screens.Settings

@Composable
fun SpeakITApp() {
    val navController = rememberNavController()

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
                startDestination = Screen.Login.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Login.route) { Login() }
                composable(Screen.Home.route) { Home() }
                composable(Screen.Library.route) { Library(onSentenceClick = { id ->
                    navController.navigate(Screen.SentenceDetails.createRoute(id))
                }) }
                composable(Screen.Settings.route) { Settings() }
                composable(route = Screen.SentenceDetails.route,
                    arguments = listOf(navArgument("sentenceId") {type = NavType.LongType})
                ) { backStackEntry ->
                    val sentenceId = backStackEntry.arguments?.getLong("sentenceId") ?: return@composable
                    SentenceDetails(
                        sentenceId = sentenceId,
                        onBack = { navController.popBackStack() }
                    )
                }
                composable(Screen.Register.route) { Register() }
            }
        }
    }
}