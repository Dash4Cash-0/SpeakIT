package com.example.speakit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.speakit.navigation.BottomNavbar
import com.example.speakit.navigation.Screen
import com.example.speakit.navigation.bottomNavScreens
import com.example.speakit.network.supabase
import com.example.speakit.screens.Home
import com.example.speakit.screens.Library
import com.example.speakit.screens.Login
import com.example.speakit.screens.Register
import com.example.speakit.screens.SentenceDetails
import com.example.speakit.screens.Settings
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus

@Composable
fun SpeakITApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = bottomNavScreens.any { it.route == backStackEntry?.destination?.route }
    val sessionStatus by supabase.auth.sessionStatus.collectAsState()

    if (sessionStatus is SessionStatus.Initializing){
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
        return
    }

    val startRoute = remember {
        if (sessionStatus is SessionStatus.NotAuthenticated) Screen.Login.route
        else Screen.Home.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (showBottomBar) BottomNavbar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .drawBehind{
                    drawRect(Color(0xFFf3f2f2))
                }
        ){
            NavHost(
                navController = navController,
                startDestination = startRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Login.route) {
                    Login(onCreateAccountClick = dropUnlessResumed{
                        navController.navigate(Screen.Register.route)
                    },
                        onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }

                    }
                }) }
                composable(Screen.Home.route) { Home() }
                composable(Screen.Library.route) { entry -> Library(onSentenceClick = { id ->
                    // dropUnlessResumed only wraps () -> Unit, so check the lifecycle by hand here
                    if (entry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate(Screen.SentenceDetails.createRoute(id))
                    }
                }) }
                composable(Screen.Settings.route) { Settings() }
                composable(route = Screen.SentenceDetails.route,
                    arguments = listOf(navArgument("sentenceId") {type = NavType.LongType})
                ) { backStackEntry ->
                    val sentenceId = backStackEntry.arguments?.getLong("sentenceId") ?: return@composable
                    SentenceDetails(
                        sentenceId = sentenceId,
                        onBack = dropUnlessResumed { navController.popBackStack() }
                    )
                }
                composable(Screen.Register.route) { Register(onBack = dropUnlessResumed { navController.popBackStack() },
                    onExistingAccount = dropUnlessResumed { navController.popBackStack() }) }
            }
        }
    }
}