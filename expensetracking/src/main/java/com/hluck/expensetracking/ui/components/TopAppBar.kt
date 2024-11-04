package com.hluck.expensetracking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.SentimentVerySatisfied
import androidx.compose.material.icons.rounded.CurrencyYen
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material.icons.sharp.Savings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.expensetracking.ui.theme.cuteFont

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppTopBarComponent() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row {
            Icon(
                imageVector = Icons.Rounded.CurrencyYen,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "记账吖",
                fontFamily = cuteFont,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Outlined.SentimentVerySatisfied,
                contentDescription = null,
                tint = Color(0xffBA283B)
            )
        }

        Row {
            Icon(
                imageVector = Icons.Outlined.Savings,
                contentDescription = null,
                tint = Color(0xffBA283B)
            )
            repeat(3){
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color(0xffBA283B)
                )
            }
        }
    }

//    TopAppBar(
//        modifier = Modifier.height(60.dp),
//        title = {
//            Text(
//                text = "记账吖",
//                fontFamily = cuteFont,
//                color = MaterialTheme.colorScheme.primary
//            )
//        },
//        navigationIcon = {
//            Icon(
//                imageVector = Icons.Rounded.CurrencyYen,
//                contentDescription = null,
//                tint = Color.White,
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.primary)
//            )
//        },
//        actions = {
//            Icon(
//                imageVector = Icons.Outlined.SentimentVerySatisfied,
//                contentDescription = null,
//                tint = Color(0xffBA283B)
//            )
//            Icon(
//                imageVector = Icons.Outlined.Savings,
//                contentDescription = null,
//                tint = Color(0xffBA283B)
//            )
//            repeat(3){
//                Icon(
//                    imageVector = Icons.Rounded.Favorite,
//                    contentDescription = null,
//                    tint = Color(0xffBA283B)
//                )
//            }
//        },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//        )
//    )
}