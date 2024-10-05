package com.hluck.hellocompose.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CustomComponent(
    canvasSize:Dp = 300.dp,
    indicateValue:Int = 0,
    maxIndicateValue:Int = 100,
    backgroundIndicateColor:Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
    backgroundIndicateStrokeWidth:Float = 100f,
    foregroundIndicateColor:Color = MaterialTheme.colorScheme.primary,
    foregroundIndicateStrokeWidth:Float = 100f,
    bigTextFontSize:TextUnit = MaterialTheme.typography.headlineLarge.fontSize,
    bigTextColor:Color = MaterialTheme.colorScheme.onSurface,
    indicateCap:StrokeCap = StrokeCap.Round,
    bigTextSuffix:String = "%",
    smallText:String = "剩余油量",
    smallTextColor:Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
    smallTextFontSize:TextUnit = MaterialTheme.typography.headlineMedium.fontSize,
    modifier: Modifier = Modifier
) {
    var arrowValue by remember { mutableStateOf(0) }
    arrowValue = if (indicateValue<=maxIndicateValue){
        indicateValue
    } else {
        maxIndicateValue
    }

    var animatedIndicateValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = arrowValue) {
        animatedIndicateValue = arrowValue.toFloat()
    }
    val percentage = (animatedIndicateValue/maxIndicateValue) *100
    val sweepValue by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000),
        label = ""
    )

    val aniValue by animateIntAsState(
        targetValue = arrowValue,
        animationSpec = tween(1000),
    )

    val aniBigTextColor by animateColorAsState(
        targetValue = if (arrowValue == 0) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else bigTextColor,
        animationSpec = tween(600)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val composeSize = size / 1.25f
                backgroundIndicator(
                    componentSize = composeSize,
                    indicateColor = backgroundIndicateColor,
                    indicatorStrokeWidth = backgroundIndicateStrokeWidth,
                    indicatorStrokeCap = indicateCap
                )
                foregroundIndicator(
                    sweepAngle = sweepValue,
                    componentSize = composeSize,
                    indicateColor = foregroundIndicateColor,
                    indicatorStrokeWidth = foregroundIndicateStrokeWidth,
                    indicatorStrokeCap = indicateCap
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmbeddedElements(
            bigText = aniValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = aniBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextColor = smallTextColor,
            smallTextSize = smallTextFontSize
        )

    }

}

@Composable
fun EmbeddedElements(
    bigText:Int,
    bigTextFontSize:TextUnit,
    bigTextColor:Color,
    bigTextSuffix:String,
    smallText:String,
    smallTextColor: Color,
    smallTextSize:TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

}

/**
 * 背景弧线
 */
fun DrawScope.backgroundIndicator(
    componentSize:Size,
    indicateColor:Color,
    indicatorStrokeWidth:Float,
    indicatorStrokeCap:StrokeCap
){
    drawArc(
        size = componentSize,
        color = indicateColor,
        startAngle = 150f,//起始角度(以度为单位)。0代表3点钟方向
        sweepAngle = 240f,//相对于startAngle顺时针旋转的角度
        useCenter = false,//是否和中心点连接
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width)/2,
            y = (size.height - componentSize.height) /2
        )
    )

}

fun DrawScope.foregroundIndicator(
    sweepAngle:Float,
    componentSize:Size,
    indicateColor:Color,
    indicatorStrokeWidth:Float,
    indicatorStrokeCap:StrokeCap
){
    drawArc(
        size = componentSize,
        color = indicateColor,
        startAngle = 150f,//起始角度(以度为单位)。0代表3点钟方向
        sweepAngle = sweepAngle,//相对于startAngle顺时针旋转的角度
        useCenter = false,//是否和中心点连接
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width)/2,
            y = (size.height - componentSize.height) /2
        )
    )

}

@Preview(showBackground = true)
@Composable
private fun CustomComponentPreview() {
    var value by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomComponent(
            indicateValue = value
        )
        TextField(
            value = value.toString(),
            onValueChange = {
                value = if(it.isEmpty()){
                    0
                } else {
                    it.toInt()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}