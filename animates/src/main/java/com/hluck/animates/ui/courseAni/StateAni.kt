package com.hluck.animates.ui.courseAni

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun StateAni(
    modifier: Modifier = Modifier
) {
    var isClick by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (isClick) 100.dp else 50.dp,
        label = "size"
    )

    val anim = remember {
        Animatable(
            initialValue = 12.dp,
            typeConverter = Dp.VectorConverter
        )
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    isClick = !isClick
                }
                .size(size)
                .background(Color.Blue)

        )
    }

}