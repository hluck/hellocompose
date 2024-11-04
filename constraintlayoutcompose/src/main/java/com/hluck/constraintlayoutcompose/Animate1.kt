package com.hluck.constraintlayoutcompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
private fun AniComponent() {
//    var size by remember { mutableStateOf(100.dp) }
    var boxSize by remember { mutableStateOf(100.dp) }
    val size by animateDpAsState(boxSize, label = "")

    val ani = remember {  Animatable(100.dp,Dp.VectorConverter) }
    var changeShadow by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = changeShadow) {
        while (true){
            delay(300)
            ani.animateTo(
                targetValue = if (changeShadow) 100.dp else 120.dp,
                animationSpec = tween(800)
            )
            changeShadow = !changeShadow
        }
    }

    var boxColor by remember { mutableStateOf(Color.Yellow) }
    val colorAni by animateColorAsState(boxColor, label = "")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
//                .offset(x = 3.dp, y = 3.dp)
                .size(ani.value)
                .clip(CircleShape)
                .background(color = Color.Green.copy(alpha = 0.1f))

        )
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .shadow(30.dp)
                .background(colorAni)
                .clickable {
                    changeShadow = !changeShadow
//                    boxSize = if (boxSize == 100.dp) {
//                        200.dp
//                    } else 100.dp
//                    boxColor = if(boxColor.value == Color.Yellow.value) Color.Blue else Color.Yellow
                }
        )
    }
}