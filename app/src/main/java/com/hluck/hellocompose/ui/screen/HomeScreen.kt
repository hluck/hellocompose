package com.hluck.hellocompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
//                navHostController.navigate(Screen.Detail.route)
                navHostController.navigate(Screen.Detail.passIdAndName(6,"小明"))
            },
            text = "Home",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = 150.dp)
                .clickable {
                    navHostController.navigate(AUTHENTICATION_ROUTE)
                },
            text = "Login/Sign Up",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium
        )


    }
}
