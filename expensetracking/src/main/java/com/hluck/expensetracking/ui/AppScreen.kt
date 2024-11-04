package com.hluck.expensetracking.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hluck.expensetracking.ui.detailscreen.DetailScreen
import com.hluck.expensetracking.ui.editscreen.EditScreen
import com.hluck.expensetracking.ui.homescreen.HomeScreen
import com.hluck.expensetracking.ui.navigation.AppNav
import com.hluck.expensetracking.ui.viewmodels.EditViewModel
import com.hluck.expensetracking.ui.viewmodels.MainViewModel

@Composable
fun AppScreen(
    mainViewModel: MainViewModel,
    editViewModel: EditViewModel
) {

    val appController = rememberNavController()

    NavHost(
        navController = appController,
        startDestination = AppNav.MAIN.name
    ) {
        composable(
            route = AppNav.MAIN.name
        ) {
            HomeScreen(
                appController,
                mainViewModel
            )
        }

        composable(
            route = AppNav.EDIT.name
        ) {
            EditScreen(
                appController,
                editViewModel
            )
        }

        composable(
            route = AppNav.DETAIL.name
        ) {
            DetailScreen(
                appController
            )
        }
    }
}