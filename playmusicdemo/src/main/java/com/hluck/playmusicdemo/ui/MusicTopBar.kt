package com.hluck.playmusicdemo.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MusicTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            {}
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }
        Spacer(Modifier.weight(1f))
        IconButton(
            {}
        ){
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menu",
                tint = Color.White
            )
        }
        IconButton(
            {}
        ){
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "moreIcon",
                tint = Color.White
            )
        }

    }

}