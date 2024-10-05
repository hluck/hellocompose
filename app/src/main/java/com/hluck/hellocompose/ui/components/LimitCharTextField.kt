package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MyComposable(modifier: Modifier = Modifier) {
    Column {
        var name by remember { mutableStateOf("") }
        val maxChars = 6
        OutlinedTextField(
            value = name,
            onValueChange = {
                if(it.length<maxChars){
                    name = it
                }
            },
            label = { Text("Name") },
            placeholder = { Text("Enter your name") },
            maxLines = 1
        )
    }
}