package com.hluck.bottomnavigationdemo2.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarModel(
    val title:String,
    val route:String,
    val selectIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNames:Boolean,
    val badges:Int
) {
    object Home:BottomBarModel(
        title = "Home",
        route = "home",
        selectIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNames = false,
        badges = 0
    )

    object Posts:BottomBarModel(
        title = "Posts",
        route = "posts",
        selectIcon = Icons.Rounded.Category,
        unselectedIcon = Icons.Outlined.Category,
        hasNames = false,
        badges = 0
    )

    object Notifications:BottomBarModel(
        title = "Notifications",
        route = "notifications",
        selectIcon = Icons.Rounded.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        hasNames = true,
        badges = 3
    )

    object Profile:BottomBarModel(
        title = "Profile",
        route = "profile",
        selectIcon = Icons.Rounded.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNames = true,
        badges = 66
    )


}