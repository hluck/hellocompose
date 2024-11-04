package com.hluck.expensetracking.ui.homescreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyYen
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hluck.expensetracking.ui.theme.cuteFont

@Preview(showBackground = true)
@Composable
fun MonthCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 10.dp,
                    start = 12.dp,
                    end = 12.dp
                )
        ) {
            Text(
                "十月支出",
                fontFamily = cuteFont,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.8f),
                        CircleShape
                    ).padding(horizontal = 12.dp, vertical = 6.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = Icons.Default.CurrencyYen,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .size(12.dp)
                )
                Text(
                    text = "9999.00",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.paddingFromBaseline(
                        top = 23.dp
                    )
//                    fontFamily = cuteFont
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.width(150.dp),
                ) {
                    Text(
                        text = "本月预算",
                        fontFamily = cuteFont,
                        modifier = Modifier.width(50.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.6f
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "点击设置",
                        modifier = Modifier.width(50.dp),
                        fontFamily = cuteFont,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.width(150.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "本月收入",
                        fontFamily = cuteFont,
                        modifier = Modifier.width(50.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.6f
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "99.99",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .paddingFromBaseline(top = 13.dp, bottom = 0.dp),
                        fontSize = 15.sp
                    )
                }
                Row(
                    modifier = Modifier.width(150.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "本月结余",
                        fontFamily = cuteFont,
                        modifier = Modifier.width(50.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.6f
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "66.66",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .paddingFromBaseline(top = 13.dp, bottom = 0.dp),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}