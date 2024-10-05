package com.hluck.lazylistdemo.ui.models

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object LazyRow : Screen("lazy_row")
    object LazyColumn : Screen("lazy_column")
    object LazyGrid : Screen("lazy_grid")
}