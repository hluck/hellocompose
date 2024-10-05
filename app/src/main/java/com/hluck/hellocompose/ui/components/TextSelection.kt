package com.hluck.hellocompose.ui.components

import android.view.textclassifier.TextSelection
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

/**
 * 让文本可选择，可复制
 */
@Preview(showBackground = true)
@Composable
private fun TextSelectionDemo() {
    HelloComposeTheme {
        Column() {
            SelectionContainer {
                Text(
                    text = "Hello compose"
                )
            }
        }
    }
}


/**
 * 让文本不可选择
 */
@Preview(showBackground = true)
@Composable
private fun TextSelectionDemo2() {
    HelloComposeTheme {
        SelectionContainer {
            Column() {
                Text("Hello Compose")
                DisableSelection {
                    Text(
                        text = "Hello compose"
                    )
                }
            }
        }

    }
}