package com.example.speakit.navigation



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier.fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        bottomNavScreens.forEach { screen ->
            val isSelected = screen.route == currentRoute
            Button(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (isSelected) Color(0xFFffe0d9) else Color(0xFFf3f2f2)
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
                Text(stringResource(screen.labelRes).uppercase(),
                    letterSpacing = 1.5.sp,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if(isSelected) Color(0xFFae1800) else Color(0xFF201e1d))
            }
        }
    }
}