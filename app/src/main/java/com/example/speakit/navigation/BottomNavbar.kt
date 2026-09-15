package com.example.speakit.navigation


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

private val bottomNavScreens = listOf(Screen.Home, Screen.Library, Screen.Settings)

@Composable
fun BottomNavbar(navController: NavController) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().shadow(elevation = 2.dp)
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .padding(6.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        bottomNavScreens.forEach { screen ->
            val isSelected = screen.route == currentRoute
            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (isSelected) Color(0xFFffe0d9) else MaterialTheme.colorScheme.surface
                ),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Text(screen.label,
                    color = Color(0xFFae1800))
            }
        }
    }
}