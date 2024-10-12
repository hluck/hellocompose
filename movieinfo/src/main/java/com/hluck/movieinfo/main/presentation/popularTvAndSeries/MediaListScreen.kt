package com.hluck.movieinfo.main.presentation.popularTvAndSeries

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hluck.movieinfo.ui.sharedcomponents.MediaItem
import com.hluck.movieinfo.ui.sharedcomponents.NonFocusedSearchBar
import com.hluck.movieinfo.ui.sharedcomponents.NonFocusedTopBar
import com.hluck.movieinfo.ui.sharedcomponents.header
import com.hluck.movieinfo.ui.theme.BigRadius
import com.hluck.movieinfo.util.BottomNavRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.truncate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaListScreen(
    selectedItem:MutableState<Int>,
    title:String = "Popular",
    navController: NavController,
    bottomBarNavController: NavHostController,
) {

    //处理返回键的事件
    BackHandler(
        enabled = true
    ) {
        selectedItem.value = 0
        bottomBarNavController.navigate(BottomNavRoute.MEDIA_HOME_SCREEN)
    }

    //下拉刷新
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val scope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        scope.launch {
            delay(1500)
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter)
                    .padding(top = BigRadius.dp),
                isRefreshing = isRefreshing,
                state = state
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val listState = rememberLazyGridState()

        LazyVerticalGrid(
            state = listState,
            contentPadding = PaddingValues(top = BigRadius.dp),
            columns = GridCells.Adaptive(190.dp)
        ) {
            header {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(
                            bottom = 6.dp,
                            start = 32.dp,
                            end = 32.dp
                        )
                        .systemBarsPadding()
                )
            }

            items(20){i ->
                MediaItem(navController)
            }

        }

        NonFocusedTopBar(
            navController = navController
        )
    }

}