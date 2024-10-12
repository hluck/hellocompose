package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.hluck.movieinfo.util.Route

@Composable
fun LazyRowItem(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val imgUrl = "http://82.157.163.217/dd.webp"
    val imgPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(Size.ORIGINAL)
            .build()
    )
    val imgState = imgPainter.state

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate(
                    "${Route.MEDIA_DETAILS_SCREEN}?id=${1}&type=type&category=category"
                )
            },
        contentAlignment = Alignment.Center
    ) {

        when(imgState){
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = imgState.painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .shimmerEffect(false)
                ) {  }
            }
            else -> {
                Icon(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(12.dp)
                )
            }
        }

    }
}