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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navController:NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier.clickable {
                navController.navigate(Screen.SignUp.route)
            }
        )
        Text(
            modifier = Modifier.padding(top = 150.dp)
                .clickable {
//                    navController.navigate(HOME_ROUTE){
//                        popUpTo(HOME_ROUTE){
//                            inclusive = true
//                        }
//                    }
                    navController.popBackStack()
                    navController.navigate(Screen.Detail.passIdAndName(2,"小明"))
                },
            text = "Go detail",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium
        )

    }
}