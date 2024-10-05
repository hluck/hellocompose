package com.hluck.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.firstcomposeapp.ui.theme.HelloComposeTheme

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
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        var state1 by remember { mutableStateOf(true) }
        Text(
            modifier = Modifier.alpha(
                if (state1) 1f else 0.3f
            ),
            text = "Text 1",
            style = MaterialTheme.typography.headlineLarge
        )
        Button(
            {
                state1 = !state1
            }
        ) {
            Text("Button 2")
        }
        Button(
            {}
        ) {
            Text("Button 3")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    HelloComposeTheme {
        SearchComponent()
    }
}


@Composable
fun SearchComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var value by remember { mutableStateOf("") }
        BasicTextField(
            value = value,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.labelLarge.fontSize
            ),
            onValueChange = { msg ->
                if (msg.isNotEmpty()){
                    value = msg
                }
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),//光标颜色
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if(value.isEmpty()){
                            Text(
                                text = "search",
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.5f
                                )
                            )
                        }
                        innerTextField()

                    }
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    if (value.isNotEmpty()){
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                value = ""
                            }
                        )
                    }
                }
            }
        )
    }
}



