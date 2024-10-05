package com.hluck.bottomnavigationdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}