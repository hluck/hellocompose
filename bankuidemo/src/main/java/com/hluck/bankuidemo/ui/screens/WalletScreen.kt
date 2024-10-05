package com.hluck.bankuidemo.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WalletScreen(
    modifier: Modifier = Modifier
) {
//    var isShowBoard by remember { mutableStateOf(true) }
    var isShowBoard by rememberSaveable { mutableStateOf(true) }
    Surface(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding()
    ) {
        if (isShowBoard){
            BoardScreen({
                isShowBoard = false
            })
        } else {
            Greetings()
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    titles: List<String> = List(1000){ "$it"}
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        items(titles){ item ->
            Greeting(item)
        }
    }
}

@Composable
private fun Greeting(title: String, modifier: Modifier = Modifier) {
//    var isExpanded by remember { mutableStateOf(false) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val paddingValue by animateDpAsState(
        targetValue = if (isExpanded) 48.dp else 0.dp,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = modifier.padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Hello",
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                if (isExpanded){
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }

            IconButton(
                { isExpanded = !isExpanded }
            ) {
                Icon(
                    imageVector = if(!isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
//            ElevatedButton(
//                onClick = {
//                    isExpanded = !isExpanded
//                },
//            ) {
//                Text(
//                    text = if (isExpanded) "Show less" else "Show more"
//                )
//            }
        }
    }
}

@Composable
fun BoardScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello,Welcome"
        )
        Button(
            onClick = {
                onClick()
            },
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            Text(
                text = "继续"
            )
        }
    }
}