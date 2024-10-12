package com.hluck.movieinfo.media_details.presentation.similar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hluck.movieinfo.R
import com.hluck.movieinfo.ui.sharedcomponents.ListShimmerEffect
import com.hluck.movieinfo.ui.sharedcomponents.MediaItem
import com.hluck.movieinfo.ui.sharedcomponents.header
import com.hluck.movieinfo.ui.theme.Radius
import com.hluck.movieinfo.ui.theme.SmallRadius

@Composable
fun SimilarMediaListScreen(
    navController:NavController,
    name:String = "The Substance"
) {
    val title = stringResource(R.string.similar_to,name)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
//        ListShimmerEffect(title = title,radius = Radius)
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(top = SmallRadius.dp),
            columns = GridCells.Adaptive(190.dp)
        ) {
            header {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .systemBarsPadding(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(
                                top = 6.dp,
                            )
                    )
                }
            }
            items(60){i ->
                MediaItem(navController = navController)
            }
        }
    }

}