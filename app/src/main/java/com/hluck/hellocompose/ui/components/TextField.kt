package com.hluck.hellocompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    var value by remember { mutableStateOf("请输入内容...") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
//        TextField( //默认样式的输入框
//        BasicTextField( //无任何样式的输入框
        OutlinedTextField( //带边框样式的输入框
            value = value,
            onValueChange = {
                value = it
            },
//            enabled = false,
//            readOnly = true,
//            singleLine = true,
            maxLines = 2,
            label = { Text("标题") },
            leadingIcon = ({
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "搜索",
                    tint = Color.Gray
                )
            }),
            trailingIcon = ({
                Icon(
                    imageVector = Icons.Filled.AssignmentTurnedIn,
                    contentDescription = ""
                )
            }),
            keyboardOptions = KeyboardOptions( //键盘配置
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions( //键盘动作配置
                onSearch = { //键盘搜索图标点击后调用的方法
                    println("搜索")
                }
            )
        )
    }
}