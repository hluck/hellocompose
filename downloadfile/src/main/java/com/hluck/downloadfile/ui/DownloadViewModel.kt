package com.hluck.downloadfile.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.downloadfile.data.RetrofitInstance
import com.hluck.downloadfile.download.DownloadRepository
import com.hluck.downloadfile.download.DownloadSaveConfig
import com.hluck.downloadfile.download.DownloadStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


fun String.logd(tag:String = "TAG"){
    Log.d(tag,this)
}

class DownloadViewModel(
    private val downloadRepository: DownloadRepository
):ViewModel() {

    private val _progressState = MutableStateFlow(0f)
    val progressState = _progressState.asStateFlow()

    fun updateProgress(progress:Float){
        _progressState.value = progress
    }

    fun downloadFile(url:String = "Apple.jpg"){
        viewModelScope.launch {
            downloadRepository.download(url, object :DownloadSaveConfig(){
                override fun saveByFileName(): String {
                    return "Apple.jpg"
                }
            }).collect{ status ->
                when(status){
                    is DownloadStatus.DownloadProcess -> updateProgress(status.progress)
                    is DownloadStatus.DownloadError -> "Error".logd()
                    is DownloadStatus.DownloadSuccess -> "Success".logd()
                }

            }
        }
    }
}