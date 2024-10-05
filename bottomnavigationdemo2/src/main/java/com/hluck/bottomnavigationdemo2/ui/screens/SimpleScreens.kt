package com.hluck.bottomnavigationdemo2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Screen(title = "Home")
}

@Composable
fun PostsScreen(modifier: Modifier = Modifier) {
    Screen(title = "Posts")
}

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    Screen(title = "Notification")
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Screen(title = "Profile")
}

@Composable
fun Screen(title:String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge
        )
    }
}