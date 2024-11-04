package com.hluck.animationbasic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun CanvasScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}