package com.hluck.composewindow

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hluck.composewindow.ui.theme.HelloComposeTheme
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity() {

    companion object{
        private var windowRef:WeakReference<Window>? = null

        fun getWindow():Window?{
            return windowRef?.get()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowRef = WeakReference(window)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        Button(
                            {
                                ComposeWindow.showWindow(this@MainActivity)
                            }
                        ) {
                            Text("打开窗口")
                        }
                    }
                }
            }
        }
    }
}
