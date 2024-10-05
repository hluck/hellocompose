package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.hluck.hellocompose.R

@Preview
@Composable
private fun CoilImage() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        val model = ImageRequest
            .Builder(LocalContext.current)
            .data("https://img1.baidu.com/it/u=3775985923,3645650044&fm=253&fmt=auto&app=138&f=JPEG?w=1468&h=800")
            .size(Size.ORIGINAL)
            .build()
        val painter = rememberAsyncImagePainter(
            model = model,
            placeholder = painterResource(R.drawable.ic_launcher_background)
        )
        val painterState = painter.state

        AsyncImage(
            model = model,
            contentDescription = null
        )
        if(painterState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        } else if (painterState is AsyncImagePainter.State.Error){
            Icon(
                imageVector = Icons.Rounded.Alarm,
                contentDescription = null
            )
        }
    }
}