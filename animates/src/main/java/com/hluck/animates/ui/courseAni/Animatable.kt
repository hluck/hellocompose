package com.hluck.animates.ui.courseAni

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun AnimatableScreen(modifier: Modifier = Modifier) {

    var isChanged by remember { mutableStateOf(false) }
    val size = remember(isChanged) { if (isChanged) 200.dp else 50.dp }
    val dpAnimatable = remember {
        Animatable(
            initialValue = size,
            typeConverter = Dp.VectorConverter
        )
    }
    LaunchedEffect(isChanged) { //配置动画规则
//        dpAnimatable.snapTo(20.dp)//设置动画过程中的初始值，瞬间完成
        dpAnimatable.animateTo(
            targetValue = size,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
//                StartOffset(300, StartOffsetType.Delay)//延迟300ms
                StartOffset(300, StartOffsetType.FastForward)//快进300ms
            ),
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(dpAnimatable.value)
                .background(MaterialTheme.colorScheme.primary)
                .clickable {
                    isChanged = !isChanged
                }
        )
    }

}