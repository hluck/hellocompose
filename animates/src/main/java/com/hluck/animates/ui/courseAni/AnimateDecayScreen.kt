package com.hluck.animates.ui.courseAni

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun AnimateDecayScreen() {
    val ani = remember {
        Animatable(0.dp, Dp.VectorConverter)
    }
    var blueValue by remember {
        mutableStateOf(ani.value)
    }
    val decay = rememberSplineBasedDecay<Dp>()
    val exponentialDecay = remember { exponentialDecay<Dp>() }
    LaunchedEffect(Unit) {
        delay(1000)
        ani.animateDecay(1000.dp, exponentialDecay){
            blueValue = value
        }
    }

    Row {
        Box(
            modifier = Modifier
                .padding(
                    0.dp, ani.value, 0.dp, 0.dp
                )
                .size(100.dp)
                .background(Color.Green)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Box(
            modifier = Modifier
                .padding(
                    0.dp, ani.value, 0.dp, 0.dp
                )
                .size(100.dp)
                .background(Color.Blue)
        )
    }
}

