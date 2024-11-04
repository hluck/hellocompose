package com.hluck.testdemo

import androidx.annotation.DrawableRes

data class ListItem(
    val title: String,
    @DrawableRes val img:Int
)
