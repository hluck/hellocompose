package com.hluck.customtopbar.ui.customtabrow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.customtopbar.ui.theme.babyPink
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestTabRow(modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        if (tabPositions.isNotEmpty()){
                            CustomIndicator(
                                tabPositions = tabPositions,
                                pagerState = pagerState
                            )
                        }
                    }
                ) {
                    for (i in 0 until 3) {
                        Tab(
                            text = { Text(text = "Tab $i") },
                            selected = pagerState.currentPage == i,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(i)
                                }
                            }
                        )
                    }
                }

                Row(
                    modifier = Modifier.weight(1f).background(babyPink)
                ) {
                    Text(text = "test")
                }
            }

            HorizontalPager(
                state = pagerState
            ) {
                Box(Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text(
                        text = "test!"
                    )
                }
            }

        }
    }
}