package com.hluck.animates.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.hluck.animates.ui.theme.ColorBlue

@Composable
fun ScreenA(
    navController:NavController
) {
    CommonScreen(
        label = "Screen A",
        icon = Icons.AutoMirrored.Filled.ArrowForward,
        screenColor = ColorBlue
    ) {
        navController.navigate(Screen.ScreenB.name)
    }
}