package com.hluck.hellocompose.ui.screen.navigation.nav_gragh

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hluck.hellocompose.ui.screen.AUTHENTICATION_ROUTE
import com.hluck.hellocompose.ui.screen.LoginScreen
import com.hluck.hellocompose.ui.screen.Screen
import com.hluck.hellocompose.ui.screen.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTHENTICATION_ROUTE
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navController)
        }
    }
}