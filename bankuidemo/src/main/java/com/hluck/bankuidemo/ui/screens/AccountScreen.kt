package com.hluck.bankuidemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

val imgUrlList = listOf(
    "http://82.157.163.217/Sunflowers.jpg",
    "http://82.157.163.217/dd.webp",
    "http://82.157.163.217/bb.png",
    "http://82.157.163.217/cc.jpeg",
    "http://82.157.163.217/dd.webp",

    "http://82.157.163.217/Sunflowers.jpg",
    "http://82.157.163.217/aa.jpg",
    "http://82.157.163.217/bb.png",
    "http://82.157.163.217/cc.jpeg",
    "http://82.157.163.217/dd.webp",
    "http://82.157.163.217/Sunflowers.jpg",
    "http://82.157.163.217/Sunflowers.jpg",
    "http://82.157.163.217/aa.jpg",
    "http://82.157.163.217/bb.png",
    "http://82.157.163.217/cc.jpeg",
    "http://82.157.163.217/dd.webp"
)

@Preview(showBackground = true)
@Composable
fun AccountScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
            .systemBarsPadding()
//            .padding(top = 18.dp)
    ) {
        itemsIndexed(imgUrlList){ index, item ->
            Column(
                modifier = Modifier.padding(10.dp)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val imageModel = ImageRequest
                    .Builder(LocalContext.current)
                    .data(item)
                    .size(coil.size.Size.ORIGINAL)
                    .build()
                val imgState = rememberAsyncImagePainter(model = imageModel).state
                when(imgState){
                    is AsyncImagePainter.State.Success -> {
                        Image(
                            painter = imgState.painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(200.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(200.dp)
                        )
                    }
                    else -> {
                        Image(
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
                Text(
                    text = "image$index",
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}