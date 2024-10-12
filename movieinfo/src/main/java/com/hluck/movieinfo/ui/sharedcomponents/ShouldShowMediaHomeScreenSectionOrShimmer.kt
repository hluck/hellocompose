package com.hluck.movieinfo.ui.sharedcomponents

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hluck.movieinfo.R
import com.hluck.movieinfo.main.presentation.home.MediaHomeScreenSection
import com.hluck.movieinfo.main.presentation.main.MainUiState
import com.hluck.movieinfo.ui.theme.Radius
import com.hluck.movieinfo.util.Constants

@Composable
fun ShouldShowMediaHomeScreenSectionOrShimmer(
    type:String = Constants.trendingAllListScreen,
    showShimmer:Boolean = false,
    navController:NavController,
    navHostController: NavHostController,
    mainUiState: MainUiState
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

    if (showShimmer){
        ShowHomeShimmer(title)
    } else {
        MediaHomeScreenSection(
            type = type,
            navController = navController,
            navHostController = navHostController,
            mainUiState = mainUiState
        )
    }

}


@Composable
fun ShowHomeShimmer(
    title:String = ""
) {
    Column {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxWidth()
                .padding(8.dp),
        )
        LazyRow {
            items(10){
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(Radius.dp))
                        .shimmerEffect(false)
                )
            }
        }
    }

}