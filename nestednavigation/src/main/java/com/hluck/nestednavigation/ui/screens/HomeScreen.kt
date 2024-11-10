package com.hluck.nestednavigation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import com.hluck.nestednavigation.ui.navgation.LOGIN_ROUTE
import com.hluck.nestednavigation.ui.navgation.NavRout

@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Home",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Magenta,
                modifier = Modifier.clickable {
                    navController.navigate(NavRout.Detail.mapperRout(1,"hello"))
                },
                fontWeight = FontWeight.Bold
            )

            Text(
                "Login/Sign up",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate(LOGIN_ROUTE)
                }
                    .padding(top = 150.dp),
            )
        }
    }
}