package com.hluck.camarademo.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.core.content.ContextCompat
import com.hluck.camarademo.R
import com.hluck.camarademo.domain.repository.CameraRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

fun String.toast(context:Context){
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()
}

class CameraRepositoryImpl @Inject constructor(
    private val application: Application
) : CameraRepository{

    private var recoding:Recording? = null

    override suspend fun takePhoto(controller: LifecycleCameraController) {
        controller.takePicture(
            ContextCompat.getMainExecutor(application),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    "拍照成功".toast(application)
                    val matrix = Matrix().apply {
                        postRotate(
                            image.imageInfo.rotationDegrees.toFloat()
                        )
                    }

                    val imageBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,0,
                        image.width,image.height,
                        matrix,true
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        savePhoto(imageBitmap)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    "拍照失败".toast(application)
                }
            }
        )
    }

    @SuppressLint("MissingPermission")
    override suspend fun recordVideo(controller: LifecycleCameraController) {
        if (recoding != null) {
            recoding?.stop()
            recoding = null
            return
        }
        val timeInMillis = System.currentTimeMillis()
        val file = File(
            application.filesDir,
            "${timeInMillis}_video" + ".mp4"
        )

        recoding = controller.startRecording(
            FileOutputOptions.Builder(file).build(),
            AudioConfig.create(true),
            ContextCompat.getMainExecutor(application)
        ) { event ->
            if (event is VideoRecordEvent.Finalize) {
                if (event.hasError()) {
                    recoding?.close()
                    recoding = null
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        saveVideo(file)
                    }
                }
            }
        }

    }


    private suspend fun savePhoto(bitmap: Bitmap){
        withContext(Dispatchers.IO){
            val resolver:ContentResolver = application.contentResolver

            val imageCollection = MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )
            val appName = application.getString(R.string.app_name)
            val timeMillis = System.currentTimeMillis()

            val imageContentValues:ContentValues = ContentValues().apply {
                put(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    "${timeMillis}_image" + ".jpg"
                )
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_DCIM + "/$appName"
                )
                put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg")
                put(MediaStore.MediaColumns.DATE_TAKEN,timeMillis)
                put(MediaStore.MediaColumns.IS_PENDING,1)
            }

            val imageMediaStoreUri:Uri? = resolver.insert(
                imageCollection,imageContentValues
            )

            imageMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.let { outputStream ->
                        bitmap.compress(
                            Bitmap.CompressFormat.JPEG,100,outputStream
                        )
                    }
                    imageContentValues.clear()
                    imageContentValues.put(
                        MediaStore.MediaColumns.IS_PENDING,0
                    )
                    resolver.update(
                        uri,imageContentValues,null,null
                    )

                } catch (e:Exception){
                    e.printStackTrace()
                    resolver.delete(uri,null,null)
                }
            }
        }
    }

    private suspend fun saveVideo(file: File) {
        withContext(Dispatchers.IO) {
            val resolver: ContentResolver = application.contentResolver

            val videoCollection = MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )

            val appName = application.getString(R.string.app_name)
            val timeInMillis = System.currentTimeMillis()

            val videoContentValues: ContentValues = ContentValues().apply {
                put(
                    MediaStore.Video.Media.DISPLAY_NAME,
                    file.name
                )
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_DCIM + "/$appName"
                )
                put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                put(MediaStore.MediaColumns.DATE_ADDED, timeInMillis / 1000)
                put(MediaStore.MediaColumns.DATE_MODIFIED, timeInMillis / 1000)
                put(MediaStore.MediaColumns.DATE_TAKEN, timeInMillis)
                put(MediaStore.Video.Media.IS_PENDING, 1)
            }

            val videoMediaStoreUri: Uri? = resolver.insert(
                videoCollection, videoContentValues
            )

            videoMediaStoreUri?.let { uri ->
                try {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        resolver.openInputStream(
                            Uri.fromFile(file)
                        )?.use { inputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }

                    videoContentValues.clear()
                    videoContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(
                        uri, videoContentValues, null, null
                    )

                } catch (e: Exception) {
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                }
            }
        }
    }
}