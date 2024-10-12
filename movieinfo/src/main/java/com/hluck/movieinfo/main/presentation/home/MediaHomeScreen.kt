package com.hluck.movieinfo.main.presentation.home

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hluck.movieinfo.R
import com.hluck.movieinfo.main.presentation.main.MainUiEvents
import com.hluck.movieinfo.main.presentation.main.MainUiState
import com.hluck.movieinfo.ui.sharedcomponents.AutoSwipeSection
import com.hluck.movieinfo.ui.sharedcomponents.NonFocusedTopBar
import com.hluck.movieinfo.ui.sharedcomponents.ShouldShowMediaHomeScreenSectionOrShimmer
import com.hluck.movieinfo.ui.sharedcomponents.shimmerEffect
import com.hluck.movieinfo.ui.theme.BigRadius
import com.hluck.movieinfo.ui.theme.MediumRadius
import com.hluck.movieinfo.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * 首页
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaHomeScreen(
    navController: NavController,
    bottomNavController: NavHostController,
    isEmpty:Boolean = false,
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {

    //滑动偏移相关
    //工具栏高度
    val toolbarHeightPx = with(LocalDensity.current){ BigRadius.dp.roundToPx().toFloat() }
    //工具栏偏移量
    var toolbarOffsetHeightPx by remember { mutableFloatStateOf(0f) }
    //嵌套滚动连接器
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                //获取垂直滚动量
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx + delta
                //限制偏移范围
                toolbarOffsetHeightPx = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    //处理返回按钮
    val context = LocalContext.current
    BackHandler(
        enabled = true
    ) {
        (context as Activity).finish()
    }

    //下拉刷新
//    var itemCount by remember { mutableIntStateOf(15) }
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()
    val refresh:() -> Unit = {
        isRefreshing = true
        refreshScope.launch {
            delay(1200)
//            onEvent(MainUiEvents.Refresh(type = Constants.homeScreen))
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = refresh,
        state = refreshState,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = (BigRadius - 8).dp),
                isRefreshing = isRefreshing,
                state = refreshState
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = BigRadius.dp)
                .systemBarsPadding()
        ) {
            ShouldShowMediaHomeScreenSectionOrShimmer(
                type = Constants.trendingAllListScreen,
                showShimmer = false,
                navController = navController,
                navHostController = bottomNavController,
                mainUiState = mainUiState
            )
            if (isEmpty){
                EmptySpecialSection(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 10.dp)
                )
            } else{
                AutoSwipeSection(navController)
            }


            Spacer(modifier = Modifier.height(8.dp))
            ShouldShowMediaHomeScreenSectionOrShimmer(
                type = Constants.recommendedListScreen,
                showShimmer = false,
                navController = navController,
                navHostController = bottomNavController,
                mainUiState = mainUiState
            )

            Spacer(modifier = Modifier.height(8.dp))
            ShouldShowMediaHomeScreenSectionOrShimmer(
                type = Constants.tvSeriesScreen,
                showShimmer = false,
                navController = navController,
                navHostController = bottomNavController,
                mainUiState = mainUiState
            )

            Spacer(modifier = Modifier.height(8.dp))
            ShouldShowMediaHomeScreenSectionOrShimmer(
                type = Constants.topRatedAllListScreen,
                showShimmer = false,
                navController = navController,
                navHostController = bottomNavController,
                mainUiState = mainUiState
            )
        }

    }

    NonFocusedTopBar(
        toolbarOffsetHeightPx = toolbarOffsetHeightPx.roundToInt(),
        navController = navController
    )
}

@Composable
fun EmptySpecialSection(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.special),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth()
            .padding(8.dp),
    )
    Box(
        modifier = modifier
            .height(220.dp)
            .fillMaxWidth()
            .padding(
                bottom = 12.dp
            )
            .clip(RoundedCornerShape(MediumRadius))
            .shimmerEffect(false)
    ) {

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TextScreen() {
    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(2000)
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                // Provide an accessible alternative to trigger refresh.
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, "Trigger Refresh")
                    }
                }
            )
        }
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(itemCount) { ListItem({ Text(text = "Item ${itemCount - it}") }) }
            }
        }
    }
}