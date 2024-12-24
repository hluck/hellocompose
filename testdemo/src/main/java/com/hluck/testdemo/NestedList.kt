package com.hluck.testdemo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

fun String.logd(tag: String = "TAG") {
    Log.d(tag, this)
}

@Preview(showBackground = true)
@Composable
fun NestedList(modifier: Modifier = Modifier) {

    // 定义滑动状态
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()

    // 保存滑动方向
    var scrollDirection by remember { mutableStateOf("None") }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(horizontalScrollState)
            .verticalScroll(verticalScrollState)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    val horizontalMove = pan.x
                    val verticalMove = pan.y

                    // 判断滑动的方向
                    when {
                        // 水平滑动的距离大于垂直滑动的距离，认为是左右滑动
                        kotlin.math.abs(horizontalMove) > kotlin.math.abs(verticalMove) -> {
                            scrollDirection = "Horizontal :${horizontalMove}"
                            scope.launch {
                                horizontalScrollState.scrollTo(horizontalScrollState.value - horizontalMove.toInt())
                            }
                        }
                        // 垂直滑动的距离大于水平滑动的距离，认为是上下滑动
                        kotlin.math.abs(verticalMove) > kotlin.math.abs(horizontalMove) -> {
                            scrollDirection = "Vertical"
                            scope.launch {
                                verticalScrollState.scrollTo(verticalScrollState.value - verticalMove.toInt())
                            }
                        }

                        else -> {
                            scrollDirection = "None"
                        }
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Text(
                text = "Scroll Direction: $scrollDirection",
                modifier = Modifier.padding(16.dp)
            )

            // 内容放置区域
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                // 放置需要滚动的内容
                Column {
                    repeat(30) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(4.dp)
                                .background(Color.Gray)
                                .border(2.dp, Color.Black)
                        ) {
                            Text(text = "Item $it", modifier = Modifier.padding(16.dp))
                            repeat(20) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .border(1.dp, Color.Red)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}