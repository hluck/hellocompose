package com.hluck.myquotes.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hluck.myquotes.ui.screens.homescreens.components.HeadingTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    showBackButton: Boolean = false,
    title:String = "",
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            HeadingTitle(
                title,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (showBackButton){
                IconButton(
                    onClick = { onBackPress() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}