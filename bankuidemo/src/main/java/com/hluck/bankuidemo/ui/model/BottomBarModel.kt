package com.hluck.bankuidemo.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarModel(
    val title:String,
    val selectedIcon:ImageVector,
    val unSelectedIcon:ImageVector,
    val route:String
) {

    object Home:BottomBarModel(
        "Home",
        Icons.Rounded.Home,
        Icons.Outlined.Home,
        "home"
    )

    object Wallet:BottomBarModel(
        "Wallet",
        Icons.Rounded.Wallet,
        Icons.Outlined.AccountBalanceWallet,
        "wallet"
    )

    object Notification:BottomBarModel(
        "Notification",
        Icons.Rounded.Notifications,
        Icons.Outlined.Notifications,
        "notification"
    )

    object Account:BottomBarModel(
        "Account",
        Icons.Rounded.AccountCircle,
        Icons.Outlined.AccountCircle,
        "account"
    )
}