package com.hluck.bottomnavigationdemo.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hluck.bottomnavigationdemo.BottomBarScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        BottomNavGraph(
            navController,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun BottomBar(
    navController:NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen:BottomBarScreen,
    currentDestination:NavDestination?,
    navController: NavController
) {
    NavigationBarItem(
        label = {
            Text(screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "navigation icon"
            )
        },
        selected = currentDestination?.route == screen.route,
        onClick = {
            navController.navigate(screen.route){
                //返回的时候直接返回到首页
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors().copy(
            unselectedIconColor = NavigationBarItemDefaults.colors().unselectedIconColor.copy(alpha = 0.2f),
            unselectedTextColor = NavigationBarItemDefaults.colors().unselectedTextColor.copy(alpha = 0.2f),
        )
    )

}