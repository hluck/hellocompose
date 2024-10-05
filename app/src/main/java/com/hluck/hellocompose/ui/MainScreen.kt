package com.hluck.hellocompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

@Composable
fun MainScreenItem(title:String,items:List<String>){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        itemsIndexed(items){index,item->
            if (index == 0) {
                Text(modifier = Modifier.fillMaxWidth(), text = title, textAlign = TextAlign.Center)
            }
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)) {
                Text(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val list1 = listOf("蓝牙配对","蓝牙音频","蓝牙传输")
    val list2 = listOf("BLE扫描","BLE广播","BLE聊天")
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("蓝牙开发") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ))
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainScreenItem("经典蓝牙",list1)
            MainScreenItem("低功耗蓝牙",list2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HelloComposeTheme {
        Column {
            var name by remember { mutableStateOf("hhh") }
            Test("111"){
                println("test调用了")
            }
            println("重组了！！")
            Test2(name){
                name = "Test2调用了！"
            }
        }
    }
}

@Composable
fun Test(name: String, onClick:()-> Unit){
    Text(name, modifier = Modifier.clickable {
        onClick()
    })
    println("test")
}


@Composable
fun Test2(name: String,onClick:()-> Unit){
    Text(name, modifier = Modifier.clickable {
        onClick()
    })
}

@Stable
class User(name:String){
    var name by mutableStateOf(name)

}