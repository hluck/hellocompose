package com.hluck.hellocompose.ui.screen.navigation.nav_gragh

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.hluck.hellocompose.ui.screen.DETAIL_ARGUMENT_ID
import com.hluck.hellocompose.ui.screen.DETAIL_ARGUMENT_NAME
import com.hluck.hellocompose.ui.screen.DetailScreen
import com.hluck.hellocompose.ui.screen.HOME_ROUTE
import com.hluck.hellocompose.ui.screen.HomeScreen
import com.hluck.hellocompose.ui.screen.Screen

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ){
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_ID) {
                type = NavType.IntType
                defaultValue = 1
//                    nullable = true
            }, navArgument(DETAIL_ARGUMENT_NAME) {
                type = NavType.StringType
            }
            )
        ) {
            val id = it.arguments?.getInt(DETAIL_ARGUMENT_ID).toString()
            val name = it.arguments?.getString(DETAIL_ARGUMENT_NAME).toString()
            Log.d("Tag", id)
            Log.d("Tag", name)
            DetailScreen(navController)
        }
    }
}