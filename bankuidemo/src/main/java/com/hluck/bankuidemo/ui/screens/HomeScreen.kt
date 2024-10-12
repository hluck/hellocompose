package com.hluck.bankuidemo.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CurrencyYen
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hluck.bankuidemo.R
import com.hluck.bankuidemo.ui.model.Card
import com.hluck.bankuidemo.ui.model.Currency
import com.hluck.bankuidemo.ui.model.Finance
import com.hluck.bankuidemo.ui.theme.BlueEnd
import com.hluck.bankuidemo.ui.theme.BlueStart
import com.hluck.bankuidemo.ui.theme.GreenEnd
import com.hluck.bankuidemo.ui.theme.GreenStart
import com.hluck.bankuidemo.ui.theme.OrangeEnd
import com.hluck.bankuidemo.ui.theme.OrangeStart
import com.hluck.bankuidemo.ui.theme.PurpleEnd
import com.hluck.bankuidemo.ui.theme.PurpleStart

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

val cards = listOf(
    Card(
        cardType = "VISA",
        cardNumber = "3664 7865 3786 3976",
        cardName = "Business",
        balance = 46.467,
        color = getGradient(PurpleStart, PurpleEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "234 7583 7899 2223",
        cardName = "Savings",
        balance = 6.467,
        color = getGradient(BlueStart, BlueEnd),
    ),

    Card(
        cardType = "VISA",
        cardNumber = "0078 3467 3446 7899",
        cardName = "School",
        balance = 3.467,
        color = getGradient(OrangeStart, OrangeEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "3567 7865 3786 3976",
        cardName = "Trips",
        balance = 26.47,
        color = getGradient(GreenStart, GreenEnd),
    ),
)

val financeList = listOf(
    Finance(
        icon = Icons.Rounded.StarHalf,
        name = "My\nBusiness",
        background = OrangeStart
    ),

    Finance(
        icon = Icons.Rounded.Wallet,
        name = "My\nWallet",
        background = BlueStart
    ),

    Finance(
        icon = Icons.Rounded.StarHalf,
        name = "Finance\nAnalytics",
        background = PurpleStart
    ),

    Finance(
        icon = Icons.Rounded.MonetizationOn,
        name = "My\nTransactions",
        background = GreenStart
    ),
)

val currencies = listOf(
    Currency(
        name = "USD",
        buy = 23.35f,
        sell = 23.25f,
        icon = Icons.Rounded.AttachMoney
    ),

    Currency(
        name = "EUR",
        buy = 13.35f,
        sell = 13.25f,
        icon = Icons.Rounded.Euro
    ),

    Currency(
        name = "YEN",
        buy = 26.35f,
        sell = 26.35f,
        icon = Icons.Rounded.CurrencyYen
    ),

    Currency(
        name = "USD",
        buy = 23.35f,
        sell = 23.25f,
        icon = Icons.Rounded.AttachMoney
    ),

    Currency(
        name = "EUR",
        buy = 63.35f,
        sell = 73.25f,
        icon = Icons.Rounded.Euro
    ),

    Currency(
        name = "YEN",
        buy = 16.35f,
        sell = 16.35f,
        icon = Icons.Rounded.CurrencyYen
    ),
)

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TopContentComponent()
            IdCardComponent()
            FinanceComponent()
        }
        Box(
            modifier = Modifier
                .wrapContentHeight()
//                .padding(bottom = 63.dp)
                .align(Alignment.BottomStart)
        ) {
            BottomListComponent()
        }
    }
}

@Composable
private fun TopContentComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Wallet",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "$ 666.666",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "search icon",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp)
        )
    }
}

@Composable
private fun IdCardComponent() {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(cards){ card ->
            val img = if(card.cardType == "VISA") painterResource(R.drawable.ic_visa) else painterResource(R.drawable.ic_mastercard)
            Column(
                modifier = Modifier
                    .width(230.dp)
                    .padding(start = 3.dp, end = 12.dp,  bottom = 5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(card.color)
                    .padding(horizontal = 10.dp)
                    .clickable { }
            ) {
                Image(
                    painter = img,
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                Text(
                    text = card.cardName,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "$ ${card.balance}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = card.cardNumber,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
//                    modifier = Modifier.padding(end = 30.dp)
                )
            }
        }
    }
}

@Composable
private fun FinanceComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 10.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = "Finance",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyRow{
            items(financeList){ finance ->
                Column(
                    modifier = Modifier
                        .clickable { }
                        .width(120.dp)
                        .padding(start = 2.dp, end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(start = 8.dp, top = 12.dp, bottom = 2.dp)
                ) {
                    Icon(
                        imageVector = finance.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(finance.background)
                            .padding(12.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = finance.name,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomListComponent() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(2.dp)
    ) {
        var selected by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        selected = !selected
                    }
                    .padding(16.dp)
                    .animateContentSize()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (selected) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp)
                        .background(MaterialTheme.colorScheme.inverseSurface)
                        .padding(2.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Currencies",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseSurface)
            )
            if (selected){
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val boxScope = this@BoxWithConstraints
                    val width = boxScope.maxWidth / 3

                    Column {
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Currency",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .width(width)
                                    .padding(start = 8.dp)
                            )

                            Text(
                                text = "Buy",
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.End,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .width(width)
                            )

                            Text(
                                text = "Sell",
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.End,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .width(width)
                                    .padding(end = 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            items(currencies.size){ index ->
                                BottomSheet(index,width)
                            }
                        }
                    }

                }
            }
        }

    }
}


@Composable
fun BottomSheet(index:Int,widthValue:Dp) {
    val currency = currencies[index]
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.width(widthValue),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = currency.icon,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(GreenStart)
                    .padding(8.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = currency.name,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Text(
            text = "$ ${currency.buy}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.width(widthValue)
        )

        Text(
            text = "$ ${currency.buy}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.width(widthValue)
        )
    }
}