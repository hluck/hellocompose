package com.hluck.nestednavigation.ui.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraphScreen(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        route = "root_nav_graph"
    ) {
        homeGraph(
            navController = navController
        )

        loginGraph(
            navController = navController
        )
    }
}