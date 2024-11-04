package com.hluck.voicetotext

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.voicetotext.ui.theme.HelloComposeTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    val voiceToTextParser by lazy {
        VoiceToTextParser(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                var canRecord by remember { mutableStateOf(false) }

                val recordAudioLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { canRecord = it }
                )

                LaunchedEffect(key1 = recordAudioLauncher) {
                    recordAudioLauncher.launch(
                        Manifest.permission.RECORD_AUDIO
                    )
                }

                val state by voiceToTextParser.state.collectAsState()

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton({
                            if (state.isPeaking) {
                                voiceToTextParser.stopListening()
                            } else {
                                val languageCode = Locale.getDefault().language
                                Log.d("TAG","languageCode:$languageCode")
                                voiceToTextParser.startListening()
                            }
                        }) {
                            AnimatedContent(state.isPeaking, label = "") { isSpeaking ->
                                if (isSpeaking) {
                                    Icon(
                                        imageVector = Icons.Rounded.Stop,
                                         contentDescription = null
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Rounded.Mic,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedContent(
                            targetState = state.isPeaking, label = ""
                        ) { isSpeaking ->
                            if(isSpeaking){
                                Text(
                                    text = "说话中..."
                                )
                            } else {
                                Text(
                                    text = state.speakText.ifEmpty {
                                        "点击开始说话"
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

