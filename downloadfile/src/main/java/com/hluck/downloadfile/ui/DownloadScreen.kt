package com.hluck.downloadfile.ui

import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadProgressComponent(
    viewModel: DownloadViewModel,
    url:String
) {
//    val sliderPosition by viewModel.progressState.collectAsStateWithLifecycle()
    val sliderPosition by viewModel.getProgressState(url).collectAsStateWithLifecycle()
    val interactionSource = remember { MutableInteractionSource() }
    val trackHeight = 4.dp
    val thumbSize = DpSize(20.dp, 20.dp)
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//        Text(text = "%.2f".format(sliderPosition))
        Text(text = "${(sliderPosition*100).toInt()}%")
        Slider(
            interactionSource = interactionSource,
            modifier =
            Modifier.semantics { contentDescription = "Localized Description" }
                .requiredSizeIn(minWidth = thumbSize.width, minHeight = trackHeight),
            value = (sliderPosition*100),
            onValueChange = {  },
            valueRange = 0f..100f,
            thumb = {
                val modifier =
                    Modifier.size(thumbSize)
                        .shadow(1.dp, CircleShape, clip = false)
                        .indication(
                            interactionSource = interactionSource,
                            indication = ripple(bounded = false, radius = 20.dp)
                        )
                SliderDefaults.Thumb(interactionSource = interactionSource, modifier = modifier)
            },
            track = {
                val modifier = Modifier.height(trackHeight)
                SliderDefaults.Track(
                    sliderState = it,
                    modifier = modifier,
                    thumbTrackGapSize = 0.dp,
                    trackInsideCornerSize = 0.dp,
                    drawStopIndicator = null
                )
            }
        )
    }
}