package com.hluck.animates.ui.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ScreenA.name
    ) {
        composable(
            route = Screen.ScreenA.name
        ){
            ScreenA(navController)
        }

        composable(
            route = Screen.ScreenB.name,
            enterTransition = {
                expandVertically()
//                expandHorizontally()
//                expandIn()
//                scaleIn()
//                slideInVertically(
//                    initialOffsetY = { -it },
//                    animationSpec = tween(
//                        300,
//                        easing = FastOutLinearInEasing
//                    )
//                )
//                slideInHorizontally(
//                    initialOffsetX = {-it},
//                    animationSpec = tween(
//                        300,
//                        easing = LinearEasing
//                    )
//                )
            },
            exitTransition = {
                shrinkVertically()
//                shrinkHorizontally()
//                shrinkOut()
//                scaleOut()
//                slideOutVertically(
//                    targetOffsetY = { -it },
//                    animationSpec = tween(
//                        300,
//                        easing = FastOutLinearInEasing
//                    )
//                )
//                slideOutHorizontally(
//                    targetOffsetX = { it },
//                    animationSpec = tween(
//                        300,
//                        easing = FastOutLinearInEasing
//                    )
//                )
            }
        ){
            ScreenB(navController)
        }
    }
}