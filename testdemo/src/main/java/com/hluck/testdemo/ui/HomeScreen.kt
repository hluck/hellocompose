package com.hluck.testdemo.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.testdemo.ListItem
import com.hluck.testdemo.R
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        val list = remember { mutableStateListOf<ListItem>() }
        LazyColumn {
            itemsIndexed(list){ index,item ->
                ListItemComponent(item){
                    Toast.makeText(context,"点击了第${index+1}行",Toast.LENGTH_LONG).show()
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                {
                    if (list.size!=15){
                        if (list.size>5){
                            list.add(4, getRandomItem())
                        } else {
                            list.add(getRandomItem())
                        }
                    } else {
                        Toast.makeText(context,"不能再增加数据，已满15行",Toast.LENGTH_LONG).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.add_color)
                ),
                modifier = Modifier.width(120.dp),
            ) {
                Text(
                    text = "Add"
                )
            }

            Button(
                {
                    if (list.size>0){
                        if (list.size>3){
                            list.removeAt(2)
                        } else {
                            list.removeAt(list.size-1)
                        }
                    } else {
                        Toast.makeText(context,"不能再删除数据，已空",Toast.LENGTH_LONG).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.delete_color)
                ),
                modifier = Modifier.width(120.dp),
            ) {
                Text(
                    text = "Delete"
                )
            }
        }
    }
}


@Composable
fun ListItemComponent(
    item: ListItem = ListItem(titles[0], imgs[0]),
    click:() -> Unit
) {
    var width = 0.dp
    var height = 0.dp
    with(LocalDensity.current){
        width = 144f.toDp()
        height = 80f.toDp()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(top = 3.dp)
            .clickable {
                click()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp),
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        Box(
            modifier = Modifier.width(width)
                .height(height)
        ) {
            Image(
                painter = painterResource(item.img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
            )
            Image(
                painter = painterResource(R.drawable.frame),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}