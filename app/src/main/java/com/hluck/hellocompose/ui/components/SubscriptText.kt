package com.hluck.hellocompose.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    SubscriptText("Hello","World")
}

/**
 * 文字的下标
 */
@Composable
private fun SubscriptText(
    normalText:String,
    subscriptText:String,
    normalFontSize:TextUnit = MaterialTheme.typography.headlineMedium.fontSize,
    subscriptFontSize:TextUnit = MaterialTheme.typography.headlineSmall.fontSize,
    subscriptTextWeight:FontWeight = FontWeight.Normal

) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = normalFontSize
                )
            ){
                append(normalText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = subscriptFontSize,
                    fontWeight = subscriptTextWeight,
                    baselineShift = BaselineShift.Subscript
                )
            ){
                append(subscriptText)
            }
        }
    )
}