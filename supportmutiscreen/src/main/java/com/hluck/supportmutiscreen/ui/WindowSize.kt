package com.hluck.supportmutiscreen.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import kotlin.math.log

data class WindowSize(
    val width:WindowType,
    val height:WindowType
)

enum class WindowType{
    COMPAT,
    MEDIUM,
    EXPANDED
}


@Composable
fun rememberWindowSize(
    modifier: Modifier = Modifier
): WindowSize {
    val configuration = LocalConfiguration.current
    Log.d("Size", "${configuration.screenWidthDp} : ${configuration.screenHeightDp}")

    return WindowSize(
        width = when{
            configuration.screenWidthDp < 600 -> WindowType.COMPAT
            configuration.screenWidthDp < 840 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        },
        height = when{
            configuration.screenHeightDp < 600 -> WindowType.COMPAT
            configuration.screenHeightDp < 840 -> WindowType.MEDIUM
            else -> WindowType.EXPANDED
        }
    )
}
