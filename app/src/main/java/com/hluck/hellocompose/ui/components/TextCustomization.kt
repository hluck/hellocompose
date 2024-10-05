package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hluck.hellocompose.R
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

@Preview(showBackground = true)
@Composable
private fun TextDemo() {
    HelloComposeTheme(
        darkTheme = false
    ) {
        CustomText()
    }
}

@Composable
private fun CustomText() {
    Text(
        text = stringResource(R.string.hello_compose),
        color = Color.White,
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .width(300.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomText2() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(
                textAlign = TextAlign.Center
            )){//段落样式
                withStyle(
                    style = SpanStyle( //字体样式
                        color = Color.Blue,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                ){
                    append("A")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                ){
                    append("B")
                }
                append("C")
                append("D")
            }
        },
        modifier = Modifier.width(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomText3() {
    Text(
        text = "Hello World!".repeat(20),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}