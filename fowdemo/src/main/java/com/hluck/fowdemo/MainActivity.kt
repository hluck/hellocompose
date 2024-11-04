package com.hluck.fowdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hluck.fowdemo.ui.theme.HelloComposeTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MyViewModel>()
    private val testViewModel by viewModels<TestViewModel>()
    private val flowToStateViewModel by viewModels<FlowToStateViewModel>()

    override fun onResume() {
        super.onResume()
        "======onResume()======".logd()
    }

    override fun onStart() {
        super.onStart()
        "======onStart()=====".logd()
    }

    override fun onPause() {
        super.onPause()
        "=====onPause()======".logd()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                //冷流：一个消费者对应一个生产者，有几个消费者就有几个生产者
                //热流：多个消费者可以对应一个生产者
                launch {
                    testViewModel.flow.collect{
//                        "111collected num:${it}".logd()
                    }
                }

                launch {
                    testViewModel.flow.collect{
//                        "222collected num:${it}".logd()
                    }
                }
            }
        }


        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(
                            name = flowToStateViewModel.flow.collectAsStateWithLifecycle(0).value.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = "Hello ",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif
            )
            AnimatedContent(
                targetState = name,
                transitionSpec = {
                    slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(500)
                    ) togetherWith slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(500)
                    )
                },
                label = ""
            ) { targetValue ->
                Text(
                    text = targetValue.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif
                )
            }
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