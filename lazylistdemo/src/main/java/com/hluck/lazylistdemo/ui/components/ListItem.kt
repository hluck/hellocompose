package com.hluck.lazylistdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.lazylistdemo.R
import com.hluck.lazylistdemo.ui.models.ImageModel

@Composable
fun LazyRowItem(
    imageModel: ImageModel
) {
    Column(
        modifier = Modifier.width(200.dp).padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageModel.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = imageModel.name
        )
    }
}

@Composable
fun LazyColumnItem(
    imageModel: ImageModel
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageModel.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = imageModel.name
        )
    }
}

@Composable
fun LazyGridItem(
    imageModel: ImageModel
) {
    Column(
        modifier = Modifier.width(200.dp).padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageModel.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = imageModel.name
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyRowItemPreview() {
//    LazyColumnItem()
}