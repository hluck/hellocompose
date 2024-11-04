package com.hluck.constraintlayoutcompose.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
private fun AniComponent() {
    val ani = remember { Animatable(100.dp, Dp.VectorConverter) }
    var changeFlag by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = changeFlag) {
        delay(300)
        ani.animateTo(
            targetValue = if (changeFlag) 5.dp else 15.dp,
            animationSpec = tween(
                durationMillis = 600,
                easing = LinearOutSlowInEasing
            )
        )
        changeFlag = !changeFlag
    }

    val ani2 = remember { Animatable(100.dp, Dp.VectorConverter) }
    LaunchedEffect(key1 = changeFlag) {
        delay(300)
        ani2.animateTo(
            targetValue = if (changeFlag) 100.dp else 116.dp,
            animationSpec = tween(
                durationMillis = 600,
                easing = LinearOutSlowInEasing
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = ani.value,
                    shape = CircleShape,
                    spotColor = Color.Green
                )
                .size(ani2.value)
                .clip(CircleShape)
                .background(Color.Green.copy(alpha = 0.2f))
        )
        Box(
            modifier = Modifier
                .shadow(
                    elevation = ani.value,
                    shape = CircleShape,
                    spotColor = Color.Blue,
                    ambientColor = Color.Yellow
                )
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary)
                .clickable {
                    changeFlag = !changeFlag
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

