package com.hluck.supportmutiscreen.ui

import android.app.LauncherActivity.ListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ItemList() {
    val windowSize = rememberWindowSize()
    if (windowSize.width== WindowType.COMPAT){
        CompactItemList()
    } else {
        MediumToExpandedItemList()
    }
}

@Composable
private fun MediumToExpandedItemList() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp)
    ){
        items(20) { index ->
            MyListItem(msg = "item $index")
        }
    }
}

@Composable
private fun CompactItemList() {
    LazyColumn {
        items(20) { index ->
            MyListItem(msg = "item $index")
        }
    }
}

@Composable
private fun MyListItem(
    msg:String = "item"
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
    }

}