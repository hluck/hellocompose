package com.hluck.downloadfile.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.downloadfile.data.RetrofitInstance
import com.hluck.downloadfile.download.DownloadRepository
import com.hluck.downloadfile.download.DownloadSaveConfig
import com.hluck.downloadfile.download.DownloadStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


fun String.logd(tag:String = "TAG"){
    Log.d(tag,this)
}

class DownloadViewModel(
    private val downloadRepository: DownloadRepository
):ViewModel() {

    // 使用 Map 来保存每个 URL 对应的进度状态
    private val progressStates = mutableMapOf<String, MutableStateFlow<Float>>()

    // 获取某个 URL 的下载进度
    fun getProgressState(url: String): StateFlow<Float> {
        // 如果进度状态还没有创建，先创建一个
        if (!progressStates.containsKey(url)) {
            progressStates[url] = MutableStateFlow(0f)
        }
        return progressStates[url]!!
    }

    // 更新下载进度
    private fun updateProgress(url: String, progress: Float) {
        progressStates[url]?.value = progress
    }

//    private val _progressState = MutableStateFlow(0f)
//    val progressState = _progressState.asStateFlow()
//
//    fun updateProgress(progress:Float){
//        _progressState.value = progress
//    }

    fun downloadFile(url:String = "Apple.jpg"){
        viewModelScope.launch {
            downloadRepository.download(url, object :DownloadSaveConfig(){
                override fun saveByFileName(): String {
                    return "Apple.jpg"
                }
            }).collect{ status ->
                when(status){
                    is DownloadStatus.DownloadProcess -> updateProgress(url,status.progress)
                    is DownloadStatus.DownloadError -> "Error".logd()
                    is DownloadStatus.DownloadSuccess -> "Success".logd()
                }

            }
        }
    }
}