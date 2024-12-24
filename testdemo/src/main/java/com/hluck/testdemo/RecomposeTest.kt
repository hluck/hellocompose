package com.hluck.testdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun RecomposeTest(modifier: Modifier = Modifier) {
    val list = remember { mutableStateListOf(1, 2, 3, 4) }
    var defaultIndex by remember { mutableStateOf(3) }

    LaunchedEffect(Unit) {
//        delay(5000)
//        defaultIndex = 4
    }
    Column {
        Test() {
            defaultIndex = 4
        }
        AnimatedVisibility(
            visible = defaultIndex == 4
        ) {
            Column {
                (2..3).forEach { index ->
                    Text(text = "text:${list[index]}")
                }
            }
        }

    }
}

@Composable
private fun Test(
    list: List<Int> = listOf(1, 2, 3, 4),
    callBack: () -> Unit = {}
) {
    Column() {
        println("重组了")
        repeat(2) {
            Text(
                text = "text:${list[it]}"
            )
        }
        LaunchedEffect(Unit) {
            delay(5000)
            callBack()
        }
//        Text("recompose")
    }
}