package com.hluck.downloadfile.download

import android.net.Uri
import com.hluck.downloadfile.App
import java.io.File

open class DownloadSaveConfig {
    open fun saveByFileName():String? = null
    open fun saveByUri(contentType:String):Uri? = null
    open fun saveByFile():File? = null

    fun getContext() = App.app
}