package com.hluck.animationbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.animationbasic.ui.theme.HelloComposeTheme

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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var isVisible by remember { mutableStateOf(true) }

//        var size by remember { mutableStateOf(20.dp) }
        //放大或缩小动画
        val size by animateDpAsState(
            targetValue = if (isVisible) 300.dp else 80.dp,
            label = "size",
            animationSpec = tween(
                durationMillis = 2000
            )
        )

        //颜色动画
        val color by animateColorAsState(
            targetValue = if (isVisible) Color.Blue else Color.Green,
            label = "color",
            animationSpec = tween(
                durationMillis = 2000
            )
        )

        //无限循环动画
        val transition = rememberInfiniteTransition()
        val transitionColor by transition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = 1000
                )
            ),
            label = ""
        )
        //圆角动画
        val corner by animateDpAsState(
            targetValue = if (isVisible) 20.dp else 300.dp,
            label = "corner",
            animationSpec = tween(
                durationMillis = 2000
            )
        )

        //弹簧动画
        var size2 by remember { mutableStateOf(20.dp) }
        val animSize2 by animateDpAsState(
            targetValue = size2,
            label = "size add",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy
            )
        )


        Button(
            {
                isVisible = !isVisible
                size2 += 60.dp
//                size = 300.dp
            }
        ) {
            Text("Animation")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .size(animSize2)
                .padding(16.dp)
//                .clip(RoundedCornerShape(corner))
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 可见性动画
//            AnimatedVisibility(
//                visible = isVisible,
//                enter = slideInHorizontally() + fadeIn(),
//                exit = slideOutHorizontally() + fadeOut()
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(200.dp)
//                        .background(Color.Blue)
//                        .padding(16.dp)
//                ) {  }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloComposeTheme {
        Greeting("Android")
    }
}