package com.hluck.lazylistdemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hluck.lazylistdemo.ui.models.Screen
import com.hluck.lazylistdemo.ui.screens.HomeScreen
import com.hluck.lazylistdemo.ui.screens.LazyColumnScreen
import com.hluck.lazylistdemo.ui.screens.LazyGridScreen
import com.hluck.lazylistdemo.ui.screens.LazyRowScreen

@Composable
fun StartUpNav(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navHostController)
        }

        composable(
            route = Screen.LazyRow.route
        ){
            LazyRowScreen()
        }

        composable(
            route = Screen.LazyColumn.route
        ){
            LazyColumnScreen()
        }

        composable(
            route = Screen.LazyGrid.route
        ){
            LazyGridScreen()
        }
    }

}