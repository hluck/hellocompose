package com.hluck.myquotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hluck.myquotes.common.logd
import com.hluck.myquotes.ui.screens.detailscreens.DetailScreen
import com.hluck.myquotes.ui.screens.homescreens.HomeScreen

@Composable
fun AppNavController(modifier: Modifier = Modifier) {

    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = ScreenRoute.HOME_SCREEN
    ) {
        composable(
            route = ScreenRoute.HOME_SCREEN
        ){
            HomeScreen(
                onClick = { id ->
                    controller.navigate(ScreenRoute.DETAIL_SCREEN+"/$id")
                }
            )
        }

        composable(
            route = ScreenRoute.DETAIL_SCREEN+"/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                }
            )
        ){
            val id = it.arguments?.getString("id")
            "id::: $id".logd()
            DetailScreen(
            ){
                controller.popBackStack()
            }
        }
    }
}