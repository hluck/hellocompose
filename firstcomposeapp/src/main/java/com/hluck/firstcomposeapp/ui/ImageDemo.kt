package com.hluck.firstcomposeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.hluck.firstcomposeapp.R
import com.hluck.firstcomposeapp.ui.theme.font

@Composable
fun ImageDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "加载本地图片",
            style = MaterialTheme.typography.labelLarge,
            fontSize = 23.sp
        )
        Image(
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp)),
//            painter = painterResource(R.drawable.test),
            painter = painterResource(R.drawable.a2),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        val imgUrl = "http://82.157.163.217/Sunflowers.jpg"

        val imgModel = ImageRequest
            .Builder(LocalContext.current)
            .data(imgUrl)
            .size(Size.ORIGINAL)
            .build()

        val imageState = rememberAsyncImagePainter(model = imgModel).state


        Spacer(modifier = Modifier.height(10.dp))
//        AsyncImage(
//            modifier = Modifier
//                .clip(RoundedCornerShape(12.dp)),
//            model = imgModel,
//            contentDescription = null
//        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {

            when(imageState){
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,
//                painter = imageState.result.drawable.toBitmap().asImageBitmap(),
                        painter = imageState.painter,
                        contentDescription = null,
                    )
                }
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                else -> {
                    Icon(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.Center),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ImageDemoPreview() {
    ImageDemo()
}