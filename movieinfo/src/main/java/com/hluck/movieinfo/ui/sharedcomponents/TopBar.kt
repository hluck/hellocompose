package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hluck.movieinfo.R
import com.hluck.movieinfo.ui.theme.BigRadius
import com.hluck.movieinfo.util.Route

@Composable
fun NonFocusedTopBar(
    toolbarOffsetHeightPx:Int = 0,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
            .height(BigRadius.dp)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx) },
        contentAlignment = Alignment.Center
    ) {
        NonFocusedSearchBar(
            modifier = Modifier
                .height(40.dp)
                .clickable {
                    navController.navigate(Route.SEARCH_SCREEN)
                }
                .padding(horizontal = 8.dp),
            placeholder = stringResource(R.string.search_movie_tip)
        )

    }
}

@Composable
fun FocusedTopBar(
    toolbarOffsetHeightPx:Int = 0
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .systemBarsPadding()
            .height(BigRadius.dp)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx) }
    ) {
        FocusedSearchBar(
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 8.dp),
            placeholder = stringResource(R.string.search_movie_tip)
        )

    }
}