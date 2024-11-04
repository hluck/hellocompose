package com.hluck.roomdemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hluck.roomdemo.ui.viewmodels.BookViewmodel

@Composable
fun HomeScreen(
    viewmodel: BookViewmodel,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = ScreenRout.MAIN.name
    ) {
        composable(
            route = ScreenRout.MAIN.name
        ) {
            MainScreen(
                navController,
                viewmodel
            )
        }

        composable(
            route = ScreenRout.DETAIL.name + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id")
            DetailScreen(
                id = id!!,
                viewmodel = viewmodel,
                navController = navController
            )
        }
    }
}