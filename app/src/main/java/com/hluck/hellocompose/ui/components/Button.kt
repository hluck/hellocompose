package com.hluck.hellocompose.ui.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.hellocompose.R

@Preview
@Composable
private fun GoogleButtonPreview() {
    GoogleButton(onClick = {Log.d("TAG","Clicked!")})
}

@Composable
fun GoogleButton(
    text:String = "sign up with Google",
    loadingText:String = "Creating account...",
    icon:Painter = painterResource(R.drawable.baseline_cruelty_free_24),
    shape:Shape = ShapeDefaults.Medium,
    borderColor: Color = Color.LightGray,
    backgroundColor:Color = MaterialTheme.colorScheme.surface,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    Surface(
        onClick = {clicked = !clicked },
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
        ) {
            Icon(
                painter = icon,
                contentDescription = "google icon",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(if(clicked) loadingText else text)
            if(clicked){
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressColor
                )
                onClick()
            }
        }
    }
}