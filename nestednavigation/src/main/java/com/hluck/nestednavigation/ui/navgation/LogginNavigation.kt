package com.hluck.nestednavigation.ui.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hluck.nestednavigation.ui.screens.LoginScreen
import com.hluck.nestednavigation.ui.screens.SignUpScreen

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {

    navigation(
        startDestination = NavRout.Login.route,
        route = LOGIN_ROUTE
    ){
        composable(
            route = NavRout.Login.route
        ){
            LoginScreen(
                navController = navController
            )
        }

        composable(
            route = NavRout.SignUp.route
        ){
            SignUpScreen(
                navController = navController
            )
        }
    }
}