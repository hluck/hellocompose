package com.hluck.movieinfo.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.movieinfo.ui.sharedcomponents.FocusedSearchBar
import com.hluck.movieinfo.ui.sharedcomponents.FocusedTopBar
import com.hluck.movieinfo.ui.theme.BigRadius

@Preview
@Composable
fun SearchScreen(

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        FocusedTopBar()

    }

}