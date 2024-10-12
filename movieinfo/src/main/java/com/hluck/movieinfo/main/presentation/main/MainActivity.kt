package com.hluck.movieinfo.main.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hluck.movieinfo.main.presentation.home.MediaHomeScreen
import com.hluck.movieinfo.main.presentation.home.MediaHomeScreenSection
import com.hluck.movieinfo.media_details.presentation.details.MediaDetailsScreen
import com.hluck.movieinfo.media_details.presentation.similar.SimilarMediaListScreen
import com.hluck.movieinfo.search.SearchScreen
import com.hluck.movieinfo.ui.theme.HelloComposeTheme
import com.hluck.movieinfo.util.Route
import dagger.hilt.android.AndroidEntryPoint


//"http://82.157.163.217/Sunflowers.jpg",
//"http://82.157.163.217/dd.webp",
//"http://82.157.163.217/bb.png",
//"http://82.157.163.217/cc.jpeg",
//"http://82.157.163.217/dd.webp",

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // SplashScreen启动画面
        installSplashScreen()
        setContent {
            HelloComposeTheme {
                val mainViewModel = hiltViewModel<MainViewModel>()
                val mainUiState = mainViewModel.mainUiState.collectAsState().value
                Navigation(
                    mainUiState = mainUiState,
                    onEvent = mainViewModel::onEvent
                )
            }
        }
    }
}


@Composable
fun Navigation(
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.MEDIA_MAIN_SCREEN
    ) {

        composable(
            route = Route.MEDIA_MAIN_SCREEN
        ) {
            MediaMainScreen(
                navController = navController,
                mainUiState = mainUiState,
                onEvent = onEvent
            )
        }

        composable(
            route = Route.SEARCH_SCREEN
        ) {
            SearchScreen()
        }

        composable(
            route = "${Route.MEDIA_DETAILS_SCREEN}?id={id}&type={type}&category={category}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            val type = it.arguments?.getString("type") ?: ""
            val category = it.arguments?.getString("category") ?: ""

            MediaDetailsScreen(navController)
        }

        composable(
            route = "${Route.SIMILAR_MEDIA_LIST_SCREEN}?title={title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            val title = it.arguments?.getString("title") ?: ""
            SimilarMediaListScreen(navController)
        }

        composable(
            route = "${Route.WATCH_VIDEO_SCREEN}?videoId={videoId}",
            arguments = listOf(
                navArgument("videoId") {
                    type = NavType.StringType
                }
            )
        ) {
            val videoId = it.arguments?.getString("videoId") ?: ""
        }

    }
}
