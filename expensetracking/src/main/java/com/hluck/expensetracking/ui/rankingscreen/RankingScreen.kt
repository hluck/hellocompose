package com.hluck.expensetracking.ui.rankingscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleLeft
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingScreen(modifier: Modifier = Modifier) {
    var tabIndex by remember { mutableStateOf(0) }
    val options = listOf("周", "月", "年")
    var selectedOption by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryTabRow(
            modifier = Modifier.fillMaxWidth(0.3f),
            selectedTabIndex = tabIndex
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        tabIndex = 0
                    }
                    .padding(6.dp),
                textAlign = TextAlign.Center,
                text = "支出"
            )
            Text(
                modifier = Modifier
                    .clickable {
                        tabIndex = 1
                    }
                    .padding(6.dp),
                textAlign = TextAlign.Center,
                text = "收入"
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            options.forEachIndexed { index, s ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = {
                        selectedOption = index
                    },
                    selected = selectedOption == index
                ) {
                    Text(text = s)
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowCircleLeft,
                contentDescription = ""
            )
            Text(
                text = "2024年11月4日 - 10日",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Outlined.ArrowCircleRight,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        StatisticsComponent()
        Spacer(modifier = Modifier.height(8.dp))
        RankingListComponent()
    }
}


@Composable
fun StatisticsComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Payments,
                contentDescription = "payments"
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "收支统计")
        }
        Spacer(Modifier.height(3.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    "支出",
                    style = MaterialTheme.typography.labelMedium
                )
                Text("0.00")
            }
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    "收入",
                    style = MaterialTheme.typography.labelMedium
                )
                Text("0.00")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    "结余",
                    style = MaterialTheme.typography.labelMedium
                )
                Text("0.00")
            }
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    "日均支出",
                    style = MaterialTheme.typography.labelMedium
                )
                Text("0.00")
            }
        }
    }
}

@Composable
fun RankingListComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Payments,
                contentDescription = "payments"
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "支出排行榜")
        }
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(10) {
                RankListItemComponent()
            }
        }
    }
}


@Composable
fun RankListItemComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(top = 10.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier.weight(1f)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("住房")
                    Text("60.8%")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("(共1笔)")
                    Text("1306.00")
                }
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                progress = {
                    0.6f
                },
            )
        }
    }
}