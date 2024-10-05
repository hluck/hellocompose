package com.hluck.bottomnavigationdemo2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hluck.bottomnavigationdemo2.ui.models.BottomBarModel
import com.hluck.bottomnavigationdemo2.ui.screens.HomeScreen
import com.hluck.bottomnavigationdemo2.ui.screens.NotificationScreen
import com.hluck.bottomnavigationdemo2.ui.screens.PostsScreen
import com.hluck.bottomnavigationdemo2.ui.screens.ProfileScreen

@Composable
fun App(
    navController:NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton({}) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        NavHost(
            modifier = modifier.fillMaxSize().padding(it),
            navController = navController,
            startDestination = BottomBarModel.Home.route
        ) {
            composable(
                route = BottomBarModel.Home.route
            ){
                HomeScreen()
            }

            composable(
                route = BottomBarModel.Posts.route
            ){
                PostsScreen()
            }

            composable(
                route = BottomBarModel.Notifications.route
            ){
                NotificationScreen()
            }

            composable(
                route = BottomBarModel.Profile.route
            ){
                ProfileScreen()
            }
        }
    }
}


@Composable
fun BottomBar(navController:NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomBarModel.Home,
        BottomBarModel.Posts,
        BottomBarModel.Notifications,
        BottomBarModel.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    LaunchedEffect(key1 = navController) {
        navController.addOnDestinationChangedListener{controller,des,args ->
            when(des.route){
                BottomBarModel.Home.route -> selectedItem = 0
                BottomBarModel.Posts.route -> selectedItem = 1
                BottomBarModel.Notifications.route -> selectedItem = 2
                BottomBarModel.Profile.route -> selectedItem = 3
            }
        }
    }
    NavigationBar {
        items.forEachIndexed { index, bottomBarModel ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if(bottomBarModel.badges != 0 ){
                                Badge{
                                    Text(
                                        text = bottomBarModel.badges.toString()
                                    )
                                }
                            } else if(bottomBarModel.hasNames){
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selectedItem == index){
                                bottomBarModel.selectIcon
                            } else bottomBarModel.unselectedIcon,
                            contentDescription = bottomBarModel.title
                        )
                    }
                },
                label = {
                    Text(
                        text = bottomBarModel.title
                    )
                },
                selected = currentDestination?.route == bottomBarModel.route,
                onClick = {
                    selectedItem = index
                    navController.navigate(bottomBarModel.route){
//                        popUpTo(BottomBarModel.Home.route){
//                            inclusive = false
//                        }
                        //返回的时候直接返回到首页
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}