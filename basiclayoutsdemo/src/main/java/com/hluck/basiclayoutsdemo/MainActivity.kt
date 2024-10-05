package com.hluck.basiclayoutsdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.hluck.basiclayoutsdemo.ui.component.BottomNavigation
import com.hluck.basiclayoutsdemo.ui.component.HomScreen
import com.hluck.basiclayoutsdemo.ui.component.MyNavigationRail
import com.hluck.basiclayoutsdemo.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            AndroidView()
            val windowSizeClass = calculateWindowSizeClass(this)
            MyApp(windowSizeClass)
//            val name by remember { mutableStateOf("Hello") }
//            val name = remember { mutableStateOf("Hello") }
//            ProcessName(name) { }
        }
    }
}

@Composable
fun ProcessName(name:String,clicked:() -> Unit){
    //这里传的是普通字符串，不是State，无法触发刷新
    val upName by remember { derivedStateOf { name.uppercase() } }
    //这里会触发，因为remember(name)，name参数的值变了
//    val upName = remember(name) { name.uppercase() }
    Text(upName,Modifier.clickable { clicked() })
}

@Composable
fun MyApp(windowSizeClass: WindowSizeClass){
    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            MyAppPortrait()
            println("Compact")
        }
        WindowWidthSizeClass.Medium -> {
            MyAppLandscape()
            println("Medium")
        }
        WindowWidthSizeClass.Expanded -> {
            MyAppLandscape()
            println("Expanded")
        }
    }
}

@Composable
fun MyAppLandscape(){
    HelloComposeTheme {
        Row {
            MyNavigationRail()
            HomScreen()
        }
    }
}

@Composable
fun MyAppPortrait() {
    HelloComposeTheme {
        Scaffold(
            bottomBar = { BottomNavigation() }
        ) { innerPadding ->
            HomScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloComposeTheme {
    }
}