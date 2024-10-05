package com.hluck.bottomnavigationdemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hluck.bottomnavigationdemo.BottomBarScreen
import com.hluck.bottomnavigationdemo.ui.screens.HomeScreen
import com.hluck.bottomnavigationdemo.ui.screens.ProfileScreen
import com.hluck.bottomnavigationdemo.ui.screens.SettingsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(
            route = BottomBarScreen.Home.route
        ){
            HomeScreen()
        }

        composable(
            route = BottomBarScreen.Profile.route
        ){
            ProfileScreen()
        }

        composable(
            route = BottomBarScreen.Settings.route
        ){
            SettingsScreen()
        }
    }

}