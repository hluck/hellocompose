package com.hluck.downloadfile.download

import android.net.Uri

/**
 * 下载状态类
 */

sealed class DownloadStatus{
    class DownloadProcess(val currentLength: Long, val length: Long, val progress: Float) : DownloadStatus()
    class DownloadError(val t:Throwable) : DownloadStatus()
    class DownloadSuccess(val uri: Uri) : DownloadStatus()
}



