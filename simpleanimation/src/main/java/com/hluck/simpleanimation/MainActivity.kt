package com.hluck.simpleanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.hluck.simpleanimation.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var isVisible by remember {
                            mutableStateOf(false)
                        }
                        var isRounded by remember {
                            mutableStateOf(false)
                        }

                        val roundSize by animateDpAsState(
                            if (isRounded) 100.dp else 20.dp,
                            animationSpec = tween(),
                            label = ""
                        )

//                        val transition = updateTransition(
//                            targetState = isVisible,
//                            label = ""
//                        )
//
//                        val borderRadiusAni by transition.animateDp(
//                            label = "",
//                            transitionSpec = { tween(600) },
//                            targetValueByState = { isFlag ->
//                                if(isFlag) 100.dp else 0.dp
//                            }
//                        )
//
//                        val colorAni by transition.animateColor(
//                            label = "",
//                            transitionSpec = { tween() },
//                            targetValueByState = {
//                                if (it) Color.Blue else Color.Green
//                            }
//                        )


                        Button(
                            {
                                isVisible = !isVisible
                                isRounded = !isRounded
                            }
                        ) {
                            Text("切换")
                        }

                        val transitionInfinite = rememberInfiniteTransition(label = "infinite")
                        val color by transitionInfinite.animateColor(
                            initialValue = Color.Red,
                            targetValue = Color.Blue,
                            animationSpec = infiniteRepeatable(
                                animation = tween(durationMillis = 3000),
                                repeatMode = RepeatMode.Reverse
                            ),
                            label = "infiniteColor"
                        )

                        AnimatedContent(
                            targetState = isVisible,
                            label = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            content = { targetState ->
                                if(targetState) {
                                    Box(Modifier.background(Color.Red))
                                } else {
                                    Box(Modifier.background(Color.Green))
                                }
                            },
                            transitionSpec = {
                                slideInHorizontally {
                                    -it
                                } togetherWith slideOutHorizontally {
                                    it
                                }
                            }
                        )

//                        Box(
//                            modifier = Modifier
//                                .size(200.dp)
//                                .background(color)
//                        )

//                        Box(
//                            modifier = Modifier
//                                .size(200.dp)
//                                .clip(RoundedCornerShape(borderRadiusAni))
//                                .background(colorAni)
//                        )

//                        AnimatedVisibility(
//                            visible = isVisible,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .weight(1f),
//                            enter = slideInHorizontally() + fadeIn(),
//                            exit = slideOutHorizontally() + fadeOut()
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .background(Color.Red)
//                            )
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloComposeTheme {
        Greeting("Android")
    }
}