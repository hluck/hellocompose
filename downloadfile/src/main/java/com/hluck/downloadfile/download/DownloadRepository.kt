package com.hluck.downloadfile.download

import android.content.ContentResolver.MimeTypeInfo
import android.net.Uri
import android.webkit.MimeTypeMap
import com.hluck.downloadfile.data.RetrofitInstance
import com.hluck.downloadfile.ui.logd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class DownloadRepository {

    suspend fun download(url: String, config: DownloadSaveConfig) = flow<DownloadStatus> {
        val response = RetrofitInstance.api.downloadFile(url)

        response.body()?.let { responseBody ->
            val length = responseBody.contentLength()
            val contentType = responseBody.contentType().toString()
            val ios = responseBody.byteStream()
            val downloadInfo = try {
                getDownloadInfo(config,contentType)
            } catch (e:Exception){
                emit(DownloadStatus.DownloadError(e))
                DownloadInfo()
                return@flow
            }

            val outputSteam = downloadInfo.outputSteam
            if (downloadInfo == null){
                emit(DownloadStatus.DownloadError(RuntimeException("下载出错！")))
                return@flow
            }
            //下载长度
            var currentLength = 0L
            //写入文件
            val bufferSize = 1024 * 8
            val buffer = ByteArray(bufferSize)
            val bufferedInputStream = BufferedInputStream(ios,bufferSize)
            var readLength = 0
            while (bufferedInputStream.read(buffer,0,bufferSize).also { readLength = it } != -1){
                currentLength += readLength
                outputSteam?.write(buffer,0,readLength)
                emit(
                    DownloadStatus.DownloadProcess(
                        currentLength.toLong(),
                        length,
                        currentLength.toFloat() / length.toFloat()
                    )
                )
            }
            bufferedInputStream.close()
            outputSteam?.close()
            ios.close()
            if (downloadInfo.uri != null){
                emit(DownloadStatus.DownloadSuccess(downloadInfo.uri))
            } else {
                emit(DownloadStatus.DownloadSuccess(Uri.fromFile(downloadInfo.file)))
            }
        } ?: kotlin.run {
            emit(DownloadStatus.DownloadError(RuntimeException("下载出错")))
        }
    }.flowOn(Dispatchers.IO)

    private fun getDownloadInfo(config: DownloadSaveConfig, contentType: String): DownloadInfo {
        val context = config.getContext()
        val uri = config.saveByUri(contentType)
        if (config.saveByFile() != null) {
            val file = config.saveByFile()
            return DownloadInfo(
                FileOutputStream(file), file)
        } else if (uri != null){
            return DownloadInfo(context.contentResolver.openOutputStream(uri),uri = uri)
        } else {
            val name = config.saveByFileName()
            val fileName = if(!name.isNullOrBlank()) name else "${System.currentTimeMillis()}.${MimeTypeMap.getSingleton().getExtensionFromMimeType(contentType)}"
            val file = File("${context.filesDir}",fileName)
            file.absolutePath.logd()
            return DownloadInfo(FileOutputStream(file),file)
        }
    }

    private class DownloadInfo(
        val outputSteam: OutputStream? = null,
        val file: File? = null,
        val uri: Uri? = null
    )
}