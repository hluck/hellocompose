package com.hluck.lazylistdemo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.lazylistdemo.data.Repository
import com.hluck.lazylistdemo.ui.components.LazyGridItem
import com.hluck.lazylistdemo.ui.models.ImageModel

@Composable
fun LazyGridScreen(
    listItems: List<ImageModel> = Repository.imageList
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(2.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(listItems){index,imageModel ->
            LazyGridItem(imageModel)
        }
    }
}

@Preview
@Composable
private fun LazyGridScreenPreview() {
    LazyGridScreen()
}