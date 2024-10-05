package com.hluck.canvasbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.canvasbasic.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {

            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        //获取宽高像素
        val widht = size.width
        val height = size.height

        val retWidth = 60.dp.toPx()
        val retHeight = 60.dp.toPx()

        //画矩形
        drawRect(
            color = Color.Yellow,
            size = Size(retWidth,retHeight),
            topLeft = Offset(widht / 2 - retWidth / 2, height / 2 - retHeight / 2)
        )

        //画圆
        drawCircle(
            brush = Brush.horizontalGradient(
                listOf(Color.Blue,Color.Yellow) //渐变颜色
            ),
            center = Offset(
                x = widht / 2,
                y = height / 2
            ),
            radius = 100.dp.toPx()
        )

        //画三角形
        val rectanglePath = Path().apply {
            //起点
            moveTo(
                x = widht/2,
                y = height - 100.dp.toPx()
            )

            lineTo(
                x = widht/2 - 50.dp.toPx(),
                y = height - 10.dp.toPx()
            )

            lineTo(
                x = widht/2 + 50.dp.toPx(),
                y = height - 10.dp.toPx()
            )

            lineTo(
                x = widht/2,
                y = height - 100.dp.toPx()
            )
        }

        drawPath(
            path = rectanglePath,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloComposeTheme {
        CanvasScreen()
    }
}