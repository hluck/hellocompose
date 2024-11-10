package com.hluck.nestednavigation.ui.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.hluck.nestednavigation.ui.screens.DetailScreen
import com.hluck.nestednavigation.ui.screens.HomeScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(
        startDestination = NavRout.Home.route,
        route = HOME_ROUTE
    ){
        composable(
            route = NavRout.Home.route
        ){
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = NavRout.Detail.route,
            arguments = listOf(
                navArgument(
                    name = "id",
                ){
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("name"){
                    type = NavType.StringType
                }
            )
        ){
            val id = it.arguments?.getInt("id") ?: 0
            val name = it.arguments?.getString("name") ?: ""
            DetailScreen(
                id,
                name,
                navController = navController
            )
        }
    }
}