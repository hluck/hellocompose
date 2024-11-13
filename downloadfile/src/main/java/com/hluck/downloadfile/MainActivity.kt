package com.hluck.downloadfile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hluck.downloadfile.download.DownloadRepository
import com.hluck.downloadfile.ui.DownloadProgressComponent
import com.hluck.downloadfile.ui.DownloadViewModel
import com.hluck.downloadfile.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DownloadViewModel>(
        factoryProducer = {
            object :ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DownloadViewModel(DownloadRepository()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        viewModel.downloadFile("Apple.jpg")
        val urls = listOf("Apple.jpg","Watermelons.jpg")
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button({
                                viewModel.downloadFile()
                            }) {
                                Text(text = "点我下载")
                            }
                            DownloadProgressComponent(
                                viewModel,
                                urls[0]
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button({
                                viewModel.downloadFile(urls[1])
                            }) {
                                Text(text = "点我下载")
                            }
                            DownloadProgressComponent(
                                viewModel,
                                urls[1]
                            )
                        }

                    }
                }
            }
        }
    }
}
