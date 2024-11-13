package com.hluck.downloadfile.common.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import com.hluck.downloadfile.ui.logd
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import kotlin.random.Random


/**
 * android10以上 uri转file uri转真实路径
 */
object FileUtil {
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun uriToFileQ(context: Context, uri: Uri): File? =
        if (uri.scheme == ContentResolver.SCHEME_FILE)
            File(requireNotNull(uri.path))
        else if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            //把文件保存到沙盒
            val contentResolver = context.contentResolver
            val displayName = "${System.currentTimeMillis()}${Random.nextInt(0, 9999)}.${
                MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(contentResolver.getType(uri))}"
            val ios = contentResolver.openInputStream(uri)
            if (ios != null) {
                File("${context.cacheDir.absolutePath}/$displayName")
                    .apply {
                        val fos = FileOutputStream(this)
                        FileUtils.copy(ios, fos)
                        fos.close()
                        ios.close()
                    }
            } else null
        } else null

    /**
     * Silent install
     *
     * @param path Package
     * @return true: success false: failed
     */
    fun installSilent(path: String): Boolean {
        "path : $path".logd()
        var result = false
        var es: BufferedReader? = null
        var os: DataOutputStream? = null

        try {
            val process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process.outputStream)

            val command = "pm install -r $path\n"
            os.write(command.toByteArray(Charset.forName("utf-8")))
            os.flush()
            os.writeBytes("exit\n")
            os.flush()

            process.waitFor()
            es = BufferedReader(InputStreamReader(process.errorStream))

            var line: String?
            val builder = StringBuilder()
            while ((es.readLine().also { line = it }) != null) {
                builder.append(line)
            }
            "install msg is $builder".logd()

            /* Installation is considered a Failure if the result contains
            the Failure character, or a success if it is not.
             */
            if (!builder.toString().contains("Failure")) {
                result = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
                es?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return result
    }


    fun isRoot(): Boolean {
        val binPath = "/system/bin/su"
        val xBinPath = "/system/xbin/su"
        if (File(binPath).exists() && isExecutable(binPath)) return true
        if (File(xBinPath).exists() && isExecutable(xBinPath)) return true
        return false
    }

    private fun isExecutable(filePath: String): Boolean {
        var p: Process? = null
        try {
            p = Runtime.getRuntime().exec("ls -l $filePath")
            // 获取返回内容
            val `in` = BufferedReader(InputStreamReader(p.inputStream))
            val str = `in`.readLine()
            if (str != null && str.length >= 4) {
                val flag = str[3]
                if (flag == 's' || flag == 'x') return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            p?.destroy()
        }
        return false
    }

}