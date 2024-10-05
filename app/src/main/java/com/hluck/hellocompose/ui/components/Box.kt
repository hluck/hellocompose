package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun BoxDemo() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter //当前box下所有内容顶部对齐
    ){
        Box(
            modifier = Modifier.background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.size(50.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomStart)
            )
            Text(
                text = "I love android.",
                fontSize = 40.sp,
                color = Color.White
            )
        }
    }
}