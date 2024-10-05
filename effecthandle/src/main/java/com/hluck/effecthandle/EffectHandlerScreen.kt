package com.hluck.effecthandle

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EffectHandle(
    viewModel: MyViewModel = viewModel()
) {
    val name by viewModel.nameState.collectAsState()
    //下面的代码每次重组都会调用
//    viewModel.loadName()

    //只有name值改变的时候才调用，如果传一个固定值比如true,那么就只会调用一次
    LaunchedEffect(true) {
        viewModel.loadName()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name
        )

        Button(
            { viewModel.addName() }
        ) {
            Text(
                text = "Change Text"
            )
        }
    }
}