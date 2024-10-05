package com.hluck.firstcomposeapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun StylingText(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val shadowOffset = Offset(5f,10f)
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue
                    )
                ){
                    append("Today is")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 33.sp
                    )
                ){
                    append(" a nice day!I think.")
                }
            },
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            style = TextStyle(
                shadow = Shadow( //加个阴影
                    color = Color.Gray,
                    offset = shadowOffset,
                    blurRadius = 15f
                )
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun StylingTextPreview() {
    StylingText()
}