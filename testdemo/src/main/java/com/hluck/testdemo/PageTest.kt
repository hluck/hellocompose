package com.hluck.testdemo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Test() {
    ModalNavigationDrawerWithPager(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .background(Color.Blue)
            ){
                Text(text = "Drawer Content", modifier = Modifier.padding(16.dp))
            }
        },
        pagerContent = { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (page == 0) Color.LightGray else Color.White), // 为了演示，添加背景颜色
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Page ${page + 1}")
            }
        },
        pages = 3
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ModalNavigationDrawerWithPager(
    drawerContent: @Composable () -> Unit,
    pagerContent: @Composable PagerScope.(page: Int) -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    scrimColor: Color = DrawerDefaults.scrimColor,
    pages: Int, // 页面的数量
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = drawerContent,
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        scrimColor = scrimColor
    ) {
        val pagerState = rememberPagerState { pages }

        val density = LocalDensity.current // 用于处理 dp 转 px

        // 边缘触发的距离范围（16dp）
        val edgeTriggerWidthPx = with(density) { 16.dp.toPx() }

        // 通过 PointerInput 检测触摸事件的位置
        val edgeSwipeModifier = Modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { change, dragAmount ->
                val dragX = dragAmount
                println(dragX)
            }
        }

        // 自定义 NestedScrollConnection
        val customNestedScrollConnection = remember {
            object : NestedScrollConnection {
//                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                    println("onPreScroll: available=${available.x}, source=$source")
//
//                    // 判断是否在第一页并且手势是右滑
//                    if (pagerState.currentPage == 0 && available.x > 0 && source == NestedScrollSource.Drag) {
//                        // 打开 Drawer
////                        scope.launch { drawerState.open() }
//                        return available // 拦截事件
//                    }
//                    return Offset.Zero
//                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    println("page index: ${pagerState.currentPage},onPostScroll: consumed=$consumed, available=$available, source=$source")
                    if (pagerState.currentPage == 0 && available.x > 0 && source == NestedScrollSource.Drag) {
                        scope.launch { drawerState.open() }
                    }

                    return super.onPostScroll(consumed, available, source)
                }
            }
        }

        // 添加 nestedScroll 到 Pager 的 Modifier
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = pages,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(customNestedScrollConnection), // 添加 nestedScroll
        ) { page ->
            pagerContent(page) // 渲染页面内容
        }
    }
}
