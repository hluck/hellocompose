package com.hluck.lazylistdemo.ui.models

import androidx.annotation.DrawableRes

data class ImageModel(
    val name:String,
    @DrawableRes val image:Int
)
