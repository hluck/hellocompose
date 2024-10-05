package com.hluck.bankuidemo.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hluck.bankuidemo.ui.components.MyBottomBar
import com.hluck.bankuidemo.ui.model.BottomBarModel
import com.hluck.bankuidemo.ui.screens.AccountScreen
import com.hluck.bankuidemo.ui.screens.HomeScreen
import com.hluck.bankuidemo.ui.screens.NotificationScreen
import com.hluck.bankuidemo.ui.screens.WalletScreen

@Composable
fun BankUiDemoNavGraph(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            MyBottomBar(navHostController)
        }
    ) {
        val paddingValue = it
        NavHost(
            navController = navHostController,
            startDestination = BottomBarModel.Home.route,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(
                route = BottomBarModel.Home.route
            ){
                HomeScreen()
            }

            composable(
                route = BottomBarModel.Wallet.route
            ){
                WalletScreen()
            }

            composable(
                route = BottomBarModel.Notification.route
            ){
                NotificationScreen()
            }

            composable(
                route = BottomBarModel.Account.route
            ){
                AccountScreen()
            }
        }
    }
}