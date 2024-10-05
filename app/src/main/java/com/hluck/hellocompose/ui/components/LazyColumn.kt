package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.hellocompose.data.Person
import com.hluck.hellocompose.data.Repository

@Preview(showBackground = true)
@Composable
private fun LazyColumnPreview() {
//    NormalLazyColumn()
    SectionLazyColumn()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionLazyColumn(modifier: Modifier = Modifier) {
    val sctions = listOf("A","B","C","D","E")
    LazyColumn(
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        sctions.forEach { section ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(12.dp),
                    text = "Section $section"
                )
            }
            items(10){
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Item $it from the section $section"
                )
            }
        }

    }
}

@Composable
private fun NormalLazyColumn() {
    val persons = Repository.getAll()
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
//        items(persons) {
//            PersonListItem(person = it)
//        }
        itemsIndexed(
            items = persons,
            key = { index, item -> item.id }
        ) { index, person ->
            PersonListItem(person = person)
        }
    }
}

@Composable
fun PersonListItem(
    person: Person,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = person.age.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = person.firstName,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = person.lastName,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal
        )
    }

}