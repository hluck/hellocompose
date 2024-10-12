package com.hluck.movieinfo.media_details.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.hluck.movieinfo.R
import com.hluck.movieinfo.ui.sharedcomponents.LazyRowItem
import com.hluck.movieinfo.ui.sharedcomponents.MediaItem
import com.hluck.movieinfo.ui.sharedcomponents.MovieImage
import com.hluck.movieinfo.ui.sharedcomponents.RatingBar
import com.hluck.movieinfo.ui.theme.BigRadius
import com.hluck.movieinfo.ui.theme.SmallRadius
import com.hluck.movieinfo.util.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    //下拉刷新
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val scope = rememberCoroutineScope()
    val onRefresh:() -> Unit = {
        isRefreshing = true
        scope.launch {
            delay(1500)
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        state = state,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = BigRadius.dp),
                isRefreshing = isRefreshing,
                state = state
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                VideoSection()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    PosterSection()
                    Spacer(modifier = Modifier.width(8.dp))
                    InfoSection()
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OverviewSection()
            Spacer(modifier = Modifier.height(16.dp))
            SimilarMediaSection(navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun VideoSection() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable { },
        shape = RoundedCornerShape(0),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            MovieImage()
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(50.dp)
                    .alpha(0.7f)
                    .background(Color.LightGray)
            )
            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "play media",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }

    }

}


@Composable
fun PosterSection(modifier: Modifier = Modifier) {
    val imgUrl = "http://82.157.163.217/dd.webp"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(Size.ORIGINAL)
            .build()
    )
    val state = painter.state

    Column {
        Spacer(modifier = Modifier.height(200.dp))
        Card(
            modifier = Modifier
                .width(180.dp)
                .height(250.dp)
                .padding(start = 16.dp),
            shape = RoundedCornerShape(SmallRadius.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MovieImage()
            }
        }
    }
}


@Composable
fun InfoSection(modifier: Modifier = Modifier) {

    Column {
        Spacer(modifier = Modifier.height(260.dp))
        Text(
            text = "Monster",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                startsModifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "8.5")
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "2008"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "-12",
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 0.6.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            text = "Romance"
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "1 h 30 min"
        )
    }
}


@Composable
fun OverviewSection(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "\"剧情，悬疑\"",
            style = MaterialTheme.typography.labelMedium,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = "jetpack compose".repeat(6),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
//            lineHeight = 16.sp
        )
    }
}


@Composable
fun SimilarMediaSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val title = stringResource(R.string.similar)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "See All",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                ),
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            "${Route.SIMILAR_MEDIA_LIST_SCREEN}?title=title"
                        )
                    }

            )
        }
        LazyRow {
            items(8){i ->
                LazyRowItem(navController)
            }
        }
    }
}


@Composable
fun SomethingWentWrong(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Something Wrong!!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}