package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hluck.movieinfo.ui.theme.Radius
import com.hluck.movieinfo.ui.theme.RadiusContainer

@Composable
fun ListShimmerEffect(
    title: String = "The Wedding Game",
    radius: Int
) {

    LazyVerticalGrid(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = radius.dp)
    ) {
        header {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                            top = 6.dp
                        )
                )
            }
        }
        items(50){i ->
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(RadiusContainer.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Box(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(Radius.dp))
                        .shimmerEffect(false)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .fillMaxWidth(0.9f)
                        .padding(start = 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect(false)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth(0.7f)
                        .padding(start = 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect(false)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth(0.6f)
                        .padding(start = 11.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerEffect(false)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

}