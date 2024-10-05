package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    HelloComposeTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomItem(3f,MaterialTheme.colorScheme.secondary)
            CustomItem(1f)

        }
    }
}

@Composable
private fun RowScope.CustomItem(
    weight:Float,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        color = color,
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .weight(weight)
    ) {}
}

@Composable
private fun ColumnScope.CustomItem(
    weight:Float,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        color = color,
        modifier = Modifier
            .width(50.dp)
            .weight(weight)
    ) {}
}