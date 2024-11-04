package com.hluck.myquotes.ui.screens.homescreens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.myquotes.domain.models.HomeScreenModel
import com.hluck.myquotes.domain.models.Quote

@Composable
fun HomeBody(
    model:HomeScreenModel,
    onClick: (id:String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            HeadingTitle(
                "Random Quote"
            )
            QuotesItem(
                quote = model.randomQuote ?: Quote("no author", quote = "no quote"),
                color = Color.Yellow,
                click = {onClick(model.randomQuote!!.id.toString())}
            )
        }
        item {
            HeadingTitle("Quotes")
        }
        model.allQuotes?.size?.let {
            items(it){ index ->
                val quote = model.allQuotes[index]
                QuotesItem(
                    quote = quote,
                    color = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    click = {
                        onClick(quote.id.toString())
                    }
                )
            }
        }
    }
}
