package com.hluck.hellocompose.ui.components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.hellocompose.ui.theme.blueLight
import com.hluck.hellocompose.ui.theme.redLight

@Composable
private fun GradientButton(
    text:String,
    textColor:Color,
    gradient:Brush,
    onClick:() -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
private fun GradientButtonPreview() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        GradientButton(
            text = "Button",
            textColor = Color.White,
            gradient = Brush.horizontalGradient(
                colors = listOf(blueLight, redLight)
            )
        ) { }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = {}) {
            Text("Button")
        }
    }
}