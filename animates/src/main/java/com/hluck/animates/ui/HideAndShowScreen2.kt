package com.hluck.animates.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HideAndShowScreen2(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    val animateAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        label = "animateAlpha"
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    alpha = animateAlpha
                }
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            { visible = !visible}
        ) {
            Text(
                text = if (visible) "隐藏" else "显示"
            )
        }
    }
}