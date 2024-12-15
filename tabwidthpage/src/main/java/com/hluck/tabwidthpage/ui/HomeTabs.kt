package com.hluck.tabwidthpage.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val pagerSate = rememberPagerState { HomeTabs.entries.size }
    val selectedIndex = pagerSate.currentPage

    Log.d("TAG","selectedIndex: ${selectedIndex},")

    Scaffold(
        topBar =
        {
            TopAppBar(
                title = {
                    Text(
                        text = "Home"
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            TabRow(
                selectedTabIndex = selectedIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeTabs.entries.forEachIndexed { index, homeTabs ->
                    Tab(
                        selected = selectedIndex == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            scope.launch {
                                pagerSate.animateScrollToPage(homeTabs.ordinal)
                            }
                        },
                        text = {
                            Text(
                                text = homeTabs.text
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if(selectedIndex == index) homeTabs.selectedIcon else homeTabs.unselectedIcon,
                                contentDescription = "Tab icon"
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerSate,
                modifier = Modifier.fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = HomeTabs.entries[selectedIndex].text)
                }
            }
        }
    }
}

enum class HomeTabs(
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val text:String
) {
    Shop(
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        text = "Shopping"
    ),
    Favorite(
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        text = "Favorite"
    ),
    Account(
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        text = "Star"
    ),
}