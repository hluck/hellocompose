package com.hluck.customtopbar.ui.customtabrow

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.hluck.customtopbar.ui.theme.lightBlue
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabIndicator(
    tabPositions: List<TabPosition>, // TabPosition列表
    pagerState: PagerState, // PageState用于获取当前页和切换进度
    color: Color = lightBlue, // 指示器颜色
    @FloatRange(from = 0.0, to = 1.0) percent: Float = 1f // 指示器宽度占Tab宽度的比例
) {
    // 获取当前选中的页和切换进度
    val currentPage by rememberUpdatedState(newValue = pagerState.currentPage)
    val fraction by rememberUpdatedState(newValue = pagerState.currentPageOffsetFraction)

    // 获取当前tab、前一个tab、后一个tab的TabPosition
    val currentTab = tabPositions[currentPage]
    val previousTab = tabPositions.getOrNull(currentPage - 1)
    val nextTab = tabPositions.getOrNull(currentPage + 1)

    Canvas(
        modifier = Modifier.fillMaxSize(), // 充满TabRow的大小
        onDraw = {
            // 计算指示器宽度
            val indicatorWidth = currentTab.width.toPx() * percent

            // 计算指示器x轴起始位置
            val indicatorOffset = if (fraction > 0 && nextTab != null) {
                // 正在向右滑动到下一页,在当前tab和下一tab之间插值
                lerp(currentTab.left, nextTab.left, fraction).toPx()
            } else if (fraction < 0 && previousTab != null) {
                // 正在向左滑动到上一页,在当前tab和上一tab之间插值
                lerp(currentTab.left, previousTab.left, -fraction).toPx()
            } else {
                // 未在滑动,使用当前tab的left
                currentTab.left.toPx()
            }

            // 绘制指示器
            val canvasHeight = size.height // 高度为整个Canvas高度
            drawRoundRect(
                color = color,
                topLeft = Offset( // 设置圆角矩形的起始点
                    indicatorOffset + (currentTab.width.toPx() * (1 - percent) / 2),
                    0F
                ),
                size = Size( // 设置宽高
                    indicatorWidth + indicatorWidth * abs(fraction),
                    canvasHeight
                ),
                cornerRadius = CornerRadius(26.dp.toPx()) // 圆角半径
            )
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = lightBlue, RoundedCornerShape(50))
//            .border(BorderStroke(2.dp, Color(0xFFC13D25)), RoundedCornerShape(50))
//            .zIndex(1f)
    )
}