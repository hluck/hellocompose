package com.hluck.expensetracking.ui.homescreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hluck.expensetracking.ui.components.AppTopBarComponent
import com.hluck.expensetracking.ui.components.NavigationBarWithOnlySelectedLabels
import com.hluck.expensetracking.ui.navigation.AppNav
import com.hluck.expensetracking.ui.navigation.BottomNav
import com.hluck.expensetracking.ui.rankingscreen.RankingScreen
import com.hluck.expensetracking.ui.viewmodels.MainViewModel

@Composable
fun HomeScreen(
    appController: NavController,
    mainViewModel: MainViewModel
) {
    // 定义一个可变状态 isVisible 来控制 FAB 的显示/隐藏
//    var isVisible by remember { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                Log.d("TAG", "consumed:${consumed.y}")
                val delta = consumed.y
                if (delta > 12f && mainViewModel.selectedBottomIndex == 0) {
                    mainViewModel.toggleVisibility(true)
                } else if (delta <= -10f  && mainViewModel.selectedBottomIndex == 0) {
                    mainViewModel.toggleVisibility(false)
                }
//                val newOffset = fabScrolledHeight + delta
//                fabScrolledHeight = newOffset.coerceIn(0f, scrollHeight)
                return Offset.Zero
            }
        }
    }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        topBar = { },
        bottomBar = {
            NavigationBarWithOnlySelectedLabels(
                mainViewModel,navController
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = mainViewModel.isVisible,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FloatingActionButton({
                    appController.navigate(AppNav.EDIT.name)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNav.HOME.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = BottomNav.HOME.name
            ) {
                ExpenseScreen(appController)
            }
            composable(
                route = BottomNav.RANKING.name
            ) {
                RankingScreen()
            }
        }
    }
}