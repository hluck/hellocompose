package com.hluck.helloglance.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
fun ChooseHero(modifier: Modifier = Modifier) {
    var sheshou by remember { mutableStateOf("寒冰") }

    var countDown by remember { mutableStateOf(10) }


    val tips by remember {
        derivedStateOf {
            "游戏倒计时：$countDown"//derivedStateOf 的作用是从一个或者多个 State 派生出另一个 State。如果某个状态是从其他状态对象计算或派生得出的，则可以使用 derivedStateOf。使用此函数可确保仅当计算中使用的状态之一发生变化时才会进行计算。
        }
    }
    val currentHero by rememberUpdatedState(sheshou) //在不中断协程的情况下，始终能够获取到最新的值。

    Column {
        Text("预选英雄：$sheshou")
        Button({
            sheshou = "奥巴马"
        }) {
            Text("改选奥巴马")
        }

        LaunchedEffect(Unit) {
            repeat(10){
                delay(1000)
                countDown--
            }
        }
        Text(
            text = if (countDown == 0) "最终选择的英雄是：$currentHero" else tips
        )
    }
}