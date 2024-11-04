package com.hluck.animates.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hluck.animates.ui.theme.ColorGreen

@Composable
fun ScreenB(
    navController: NavController
) {
    CommonScreen(
        label = "Screen B",
        screenColor = ColorGreen,
        icon = Icons.AutoMirrored.Filled.ArrowBack
    ) {
        navController.popBackStack()
    }
}