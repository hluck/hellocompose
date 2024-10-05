package com.hluck.hellocompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.hluck.hellocompose.ui.theme.Blue
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueListScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BlueTopBar("蓝牙配对", scrollBehavior)
        },
        bottomBar = {}
    ) { innerPadding ->
        BluePairScreen(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueTopBar(title: String, scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@Composable
fun BluePairScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                "开启搜索", style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 3.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
            Switch(modifier = Modifier.padding(horizontal = 5.dp), checked = true, onCheckedChange = {})
            Text(
                "关闭搜索", modifier = Modifier.weight(1f), textAlign = TextAlign.End,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 3.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueListScreenPreview() {
    HelloComposeTheme(darkTheme = true) {
        BlueListScreen()
    }
}