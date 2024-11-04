package com.hluck.playmusicdemo.ui

import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hluck.playmusicdemo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds

fun String.logd(tag:String = "TAG"){
    Log.d(tag, this)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicScreen(
    viewModel: MediaViewModel,
    mediaPlayer: MediaPlayer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xffb92b27),
                        Color(0xff1565C0)
                    )
                )
            )
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MusicTopBar()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(30.dp))
            Image(
                painter = painterResource(R.drawable.ai),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sizeIn(maxWidth = 360.dp, maxHeight = 360.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .weight(10f)
            )
            Spacer(Modifier.height(30.dp))
            SongDescription("Love Story", "Taylor Swift")
            Spacer(Modifier.height(30.dp))
            Column(
                modifier = Modifier.weight(10f)
            ) {
                PlayerSlider(
                    viewModel = viewModel,
                    ofSeconds = (mediaPlayer.duration/1000).seconds
                )
                Spacer(modifier = Modifier.height(40.dp))
                MusicPlayerButtons(
                    viewModel = viewModel,
                    player = mediaPlayer
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SongDescription(
    title: String,
    name: String
) {

    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White.copy(0.7f)
    )


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerSlider(
    viewModel: MediaViewModel,
    ofSeconds: Duration?
) {
    val secondsState by viewModel.currentMinutes.collectAsStateWithLifecycle()
    Column {
        Slider(
            value = secondsState.toFloat(),
            valueRange = 0f..ofSeconds?.inWholeSeconds?.toFloat()!!,
            onValueChange = {

            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(0.5f)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "${secondsState} s",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${ofSeconds.inWholeSeconds} s",
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Composable
fun MusicPlayerButtons(
    viewModel: MediaViewModel,
    player: MediaPlayer,
    modifier: Modifier = Modifier,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 42.dp
) {
    val isPaused by viewModel.isPaused.collectAsStateWithLifecycle()
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver{_,event, ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            player.stop()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val mediaScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val buttonModifier = Modifier
            .size(sideButtonSize)
            .semantics { role = Role.Button }

        Image(
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = "skip previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.Replay10,
            contentDescription = "skip previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = buttonModifier
        )

        Image(
            imageVector = if (isPaused){
                Icons.Filled.PlayCircle
            } else {
                Icons.Filled.PauseCircle
            },
            contentDescription = "skip previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(playerButtonSize)
                .clickable {
                    if (isPaused) {
                        viewModel.start()
                        player.start()
                        mediaScope.launch {
                            delay(200)
                            viewModel.getMediaDuration(player)
                        }
                    } else {
                        viewModel.pause()
                        player.pause()
                    }
                }
                .semantics {
                    role = Role.Button
                }
        )

        Image(
            imageVector = Icons.Filled.Forward10,
            contentDescription = "skip previous",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.SkipNext,
            contentDescription = "skip next",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = buttonModifier
        )
    }
}