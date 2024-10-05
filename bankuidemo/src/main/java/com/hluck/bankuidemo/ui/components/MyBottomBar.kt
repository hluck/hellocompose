package com.hluck.bankuidemo.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hluck.bankuidemo.ui.model.BottomBarModel

@Preview(showBackground = true)
@Composable
fun MyBottomBar(
    navController:NavHostController = rememberNavController()
) {
    val list = listOf(
        BottomBarModel.Home,
        BottomBarModel.Wallet,
        BottomBarModel.Notification,
        BottomBarModel.Account
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = navController ) {
        navController.addOnDestinationChangedListener{ controller,destination,args ->
            when(destination.route){
                BottomBarModel.Home.route -> selectedIndex = 0
                BottomBarModel.Wallet.route -> selectedIndex = 1
                BottomBarModel.Notification.route -> selectedIndex = 2
                BottomBarModel.Account.route -> selectedIndex = 3
            }
        }
    }

    NavigationBar {
        list.forEachIndexed { index, bottomBarModel ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == bottomBarModel.route,
                onClick = {
                    selectedIndex = index
                    navController.navigate(bottomBarModel.route){
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selectedIndex == index) bottomBarModel.selectedIcon else bottomBarModel.unSelectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = bottomBarModel.title
                    )
                }
            )
        }
    }
}