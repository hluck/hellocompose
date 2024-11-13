package com.hluck.downloadfile.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.downloadfile.App
import com.hluck.downloadfile.common.utils.FileUtil
import com.hluck.downloadfile.download.DownloadRepository
import com.hluck.downloadfile.download.DownloadSaveConfig
import com.hluck.downloadfile.download.DownloadStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.solrudev.ackpine.installer.PackageInstaller
import ru.solrudev.ackpine.installer.createSession
import ru.solrudev.ackpine.session.SessionResult
import ru.solrudev.ackpine.session.await
import ru.solrudev.ackpine.uninstaller.PackageUninstaller
import java.io.File
import kotlin.coroutines.cancellation.CancellationException


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


    val parentPath = App.app.filesDir

    fun downloadFile(url:String = "Apple.jpg"){
        viewModelScope.launch {
            downloadRepository.download(url, object :DownloadSaveConfig(){
                override fun saveByFileName(): String {
                    return url
                }
            }).collect{ status ->
                when(status){
                    is DownloadStatus.DownloadProcess -> updateProgress(url,status.progress)
                    is DownloadStatus.DownloadError -> "Error".logd()
                    is DownloadStatus.DownloadSuccess -> {
                        "Success".logd()
                        "status uri:${status.uri}".logd()
                        "parentPath:$parentPath".logd()
//                        val result = FileUtil.installSilent("${parentPath}/${url}")
//                        "result $result".logd()

                        val packageInstaller = PackageInstaller.getInstance(App.app)
                        val packageUninstaller = PackageUninstaller.getInstance(App.app)
                        try {
                            when (val result = packageInstaller.createSession(Uri.fromFile(File("${parentPath}/${url}"))).await()) {
                                is SessionResult.Success -> "Success".logd()
                                is SessionResult.Error -> result.cause.message?.logd()
                            }
                        } catch (_: CancellationException) {
                            println("Cancelled")
                        } catch (exception: Exception) {
                            exception.printStackTrace()
                        }

                    }
                }

            }
        }
    }


    fun install(apkPath:String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setDataAndType(
            Uri.fromFile(File(apkPath)),
            "application/vnd.android.package-archive"
        )
        App.app.startActivity(intent)
    }

}