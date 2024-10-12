package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hluck.movieinfo.ui.theme.Radius
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AutoSwipeImagePager(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val pageState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f,
        pageCount = { 5 }
    )
    val scope = rememberCoroutineScope()
    val swipeIntervalMillis = 3000L

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(),
            state = pageState,
            pageSize = PageSize.Fill
        ) { index ->

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(Radius.dp))
                ) {
                    PageImageItem(
                        navController = navController,
                        Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    )
                                )
                            )
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 12.dp,
                                top = 22.dp,
                            )
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "Hello Word!",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(swipeIntervalMillis)
                    scope.launch {
                        if (pageState.canScrollForward) {
                            pageState.animateScrollToPage(
                                pageState.currentPage + 1
                            )
                        } else {
                            pageState.animateScrollToPage(
                                0
                            )
                        }
                    }
                }
            }

        }

        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(5, pageState.currentPage)
        }

    }
}