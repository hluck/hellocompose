package com.hluck.checknet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.checknet.helper.ConnectionStatus
import com.hluck.checknet.helper.currentConnectivityStatus
import com.hluck.checknet.helper.observerConnectivityAsFlow
import com.hluck.checknet.ui.AvailableScreen
import com.hluck.checknet.ui.UnAvailableScreen
import com.hluck.checknet.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        MainScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val state by produceState(initialValue = context.currentConnectivityStatus) {
        context.observerConnectivityAsFlow().collect{
            value = it
        }
    }

    val isConnected = state == ConnectionStatus.Available
    if (isConnected){
        AvailableScreen()
    } else {
        UnAvailableScreen()
    }
}