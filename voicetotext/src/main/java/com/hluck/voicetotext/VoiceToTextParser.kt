package com.hluck.voicetotext

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import java.util.Locale.LanguageRange


fun String.toast(){
    Toast.makeText(App.app,this,Toast.LENGTH_LONG).show()
}

class VoiceToTextParser(
    private val app:Application
):RecognitionListener{

    private val _state = MutableStateFlow(VoiceToTextState())
    val state = _state.asStateFlow()

    val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    fun startListening(languageCode:String = "en"){

        _state.update { VoiceToTextState() }
        val isAvailable = SpeechRecognizer.isRecognitionAvailable(app)
        Toast.makeText(App.app,"isAvailable:$isAvailable",Toast.LENGTH_LONG).show()
        if (!isAvailable){
            _state.update {
                it.copy(
                    error = "Recognition is not available."
                )
            }
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            putExtra(RecognizerIntent.EXTRA_LANGUAGE,languageCode)
        }

        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)

        _state.update {
            it.copy(
                isPeaking = true
            )
        }
    }
    fun stopListening(){
        _state.update {
            it.copy(
                isPeaking = false
            )
        }
        recognizer.stopListening()
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(p0: Float) = Unit

    override fun onBufferReceived(p0: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update {
            it.copy(
                isPeaking = false
            )
        }
    }

    override fun onError(p0: Int) {
        if (p0 == SpeechRecognizer.ERROR_CLIENT){
            return
        }
        _state.update {
            it.copy(
                error = "error:$p0"
            )
        }
    }

    override fun onResults(bundle: Bundle?) {
        "onResult:$bundle".toast()
        bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { result ->
                Toast.makeText(App.app,"result:$result",Toast.LENGTH_LONG).show()
                _state.update {
                    it.copy(
                        speakText = result
                    )
                }
            }
    }

    override fun onPartialResults(p0: Bundle?) = Unit

    override fun onEvent(p0: Int, p1: Bundle?) = Unit
}

data class VoiceToTextState(
    val speakText:String = "",
    val isPeaking:Boolean = false,
    val error:String? = null
)