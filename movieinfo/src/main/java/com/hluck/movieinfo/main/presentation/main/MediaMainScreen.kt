package com.hluck.movieinfo.main.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hluck.movieinfo.main.presentation.home.MediaHomeScreen
import com.hluck.movieinfo.main.presentation.popularTvAndSeries.MediaListScreen
import com.hluck.movieinfo.util.BottomNavRoute
import com.hluck.movieinfo.util.Constants


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun MediaMainScreen(
    navController: NavController,
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Popular",
            selectedIcon = Icons.Filled.LocalFireDepartment,
            unselectedIcon = Icons.Outlined.LocalFireDepartment,
        ),
        BottomNavigationItem(
            title = "Tv Series",
            selectedIcon = Icons.Filled.LiveTv,
            unselectedIcon = Icons.Outlined.LiveTv
        )
    )

    val bottomBarNavController = rememberNavController()
    var selectedIndex = rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, bottomNavigationItem ->
                    NavigationBarItem(
                        selected = selectedIndex.value == index,
                        onClick = {
                            selectedIndex.value = index
                            when (selectedIndex.value) {
                                0 -> {
                                    bottomBarNavController.navigate(
                                        BottomNavRoute.MEDIA_HOME_SCREEN
                                    )
                                }

                                1 -> {
                                    bottomBarNavController.navigate(
                                        "${BottomNavRoute.MEDIA_LIST_SCREEN}?type=${Constants.popularScreen}"
                                    )
                                }

                                2 -> {
                                    bottomBarNavController.navigate(
                                        "${BottomNavRoute.MEDIA_LIST_SCREEN}?type=${Constants.tvSeriesScreen}"
                                    )
                                }
                            }
                        },
                        label = {
                            Text(
                                text = bottomNavigationItem.title
                            )
                        },
                        icon = {
                            if (selectedIndex.value == index) {
                                Icon(
                                    imageVector = bottomNavigationItem.selectedIcon,
                                    contentDescription = bottomNavigationItem.title
                                )
                            } else {
                                Icon(
                                    imageVector = bottomNavigationItem.unselectedIcon,
                                    contentDescription = bottomNavigationItem.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        BottomNavigationScreens(
            selectedIndex = selectedIndex,
            navController = navController,
            bottomNavController = bottomBarNavController,
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            ),
            mainUiState = mainUiState,
            onEvent = onEvent
        )
    }

}

@Composable
fun BottomNavigationScreens(
    selectedIndex: MutableState<Int>,
    navController: NavController,
    bottomNavController: NavHostController,
    modifier: Modifier = Modifier,
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = bottomNavController,
        startDestination = BottomNavRoute.MEDIA_HOME_SCREEN
    ) {
        composable(
            BottomNavRoute.MEDIA_HOME_SCREEN
        ) {
            MediaHomeScreen(
                navController = navController,
                bottomNavController = bottomNavController,
                onEvent = onEvent,
                mainUiState = mainUiState
            )
        }

        composable(
            route = "${BottomNavRoute.MEDIA_LIST_SCREEN}?type={type}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            MediaListScreen(
                selectedItem = selectedIndex,
                navController = navController,
                bottomBarNavController = bottomNavController
            )
        }
    }

}