package com.hluck.movieinfo.main.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hluck.movieinfo.R
import com.hluck.movieinfo.main.presentation.main.MainUiState
import com.hluck.movieinfo.ui.sharedcomponents.LazyRowItem
import com.hluck.movieinfo.util.BottomNavRoute
import com.hluck.movieinfo.util.Constants

@Composable
fun MediaHomeScreenSection(
    type:String = Constants.trendingAllListScreen,
    navController: NavController,
    navHostController: NavHostController,
    mainUiState: MainUiState,
) {

    val title = when(type){
        Constants.trendingAllListScreen -> {
            stringResource(R.string.trending)
        }
        Constants.tvSeriesScreen -> {
            stringResource(R.string.trending)
        }
        Constants.recommendedListScreen -> {
            stringResource(R.string.recommended)
        }
        else -> stringResource(R.string.top_rated)
    }

    val mediaList = when(type){
        Constants.trendingAllListScreen -> {
            mainUiState.trendingAllList.take(10)
        }

        Constants.tvSeriesScreen -> {
            mainUiState.tvSeriesList.take(10)
        }

        Constants.recommendedListScreen -> {
            mainUiState.recommendedAllList.take(10)
        }

        else -> {
            mainUiState.topRatedAllList.take(10)
        }
    }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "See All",
                modifier = Modifier
                    .clickable {
                        navHostController.navigate(
                            "${BottomNavRoute.MEDIA_LIST_SCREEN}?type=${type}"
                        )
                    }
            )
        }
        LazyRow {
            items(10){
                LazyRowItem(
                    navController
                )
            }
        }
    }

}