package com.hluck.lazylistdemo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.lazylistdemo.data.Repository
import com.hluck.lazylistdemo.ui.components.LazyRowItem
import com.hluck.lazylistdemo.ui.models.ImageModel

@Composable
fun LazyRowScreen(
    listItems: List<ImageModel> = Repository.imageList
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listItems){ imageModel ->
            LazyRowItem(imageModel)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun LazyRowScreenPreview() {
    LazyRowScreen()
}