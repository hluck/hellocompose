package com.hluck.lazylistdemo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.lazylistdemo.data.Repository
import com.hluck.lazylistdemo.ui.components.LazyColumnItem
import com.hluck.lazylistdemo.ui.models.ImageModel

@Composable
fun LazyColumnScreen(
    listItems: List<ImageModel> = Repository.imageList
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listItems){ imageModel ->
            LazyColumnItem(imageModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyColumnScreenPreview() {
    LazyColumnScreen()
}


