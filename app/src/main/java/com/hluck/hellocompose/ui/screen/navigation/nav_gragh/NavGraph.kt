package com.hluck.hellocompose.ui.screen.navigation.nav_gragh

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hluck.hellocompose.ui.screen.HOME_ROUTE
import com.hluck.hellocompose.ui.screen.ROOT_ROUTE
import com.hluck.hellocompose.ui.screen.Screen


@Composable
fun SetupNaviGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE
    ) {
        homeNavigationGraph(navController)
        authNavGraph(navController)
    }

}