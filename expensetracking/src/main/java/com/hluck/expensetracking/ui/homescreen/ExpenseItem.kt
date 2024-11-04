package com.hluck.expensetracking.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hluck.expensetracking.ui.navigation.AppNav
import com.hluck.expensetracking.ui.theme.customRed

@Composable
fun ExpenseItem(
    appNavController: NavController
) {
    Column {
        DayLabelComponent()
        repeat(10){
            ExpenseDetailComponent(appNavController)
        }
    }
}


@Composable
fun DayLabelComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 5.dp, vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "2024年12月26日 周日",
        )

        Row {
            Text(
                text = "支出：100",
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "收入：100",
            )
        }
    }
}

@Composable
fun ExpenseDetailComponent(
    appNavController: NavController
) {

    Row(
        modifier = Modifier
            .clickable {
                appNavController.navigate(AppNav.DETAIL.name)
            }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .shadow(elevation = 0.5.dp)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Restaurant,
                contentDescription = "meal",
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(5.dp)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "餐饮",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "-15",
            color = MaterialTheme.colorScheme.primary
        )
    }
}