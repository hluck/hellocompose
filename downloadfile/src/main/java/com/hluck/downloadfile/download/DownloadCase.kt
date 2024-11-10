package com.hluck.downloadfile.download

/**
*         val dowloadUrl = "下载地址"
*         download(dowloadUrl, object : IDowloadBuild() {
*             //返回context 这是必需的，当然这里也可以直接返回Application
*             override fun getContext(): Context = MyApplication.context
*
*             //可以实现以下三个方法中的任意一个，当然，也可以不实现，不实现的话文件名和类型会从网址获取，文件会保存到私有目录
*             override fun getDowloadFile(): File? {
*                 //返回存储下载的文件File("存储地址+文件名")，可以在这里设置保存文件地址
*                 return File(getContext().cacheDir, "app.apk")
*             }
*
*             override fun getFileName(): String? {
*                 //返回保存文件的文件名
*                 return "app.apk"
*             }
*
*             //android10之后如果下载的文件需要传递给外部app，建议直接下载成uri
*             override fun getUri(contentType: String): Uri? {
*                 //下载到共享目录，这里需要考虑android10以上
*                 val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
*                     val values = ContentValues().apply {
*                         put(MediaStore.MediaColumns.DISPLAY_NAME, "xxxx.后缀") //文件名
*                         put(MediaStore.MediaColumns.MIME_TYPE, contentType) //文件类型
*                         put(
*                             MediaStore.MediaColumns.RELATIVE_PATH,
*                             Environment.DIRECTORY_DOWNLOADS
*                         )
*                     }
*                     getContext().contentResolver.insert(
*                         MediaStore.Downloads.EXTERNAL_CONTENT_URI,
*                         values
*                     )
*                 } else
*                     Uri.fromFile(File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath + File.separator + "文件名.后缀"))
*                 return uri
*             }
*
*         })
*            .collect  {
*                 when (it) {
*                     is DowloadStatus.DowloadErron -> {
*                         //下载错误
*                     }
*                     is DowloadStatus.DowloadSuccess -> {
*                         //下载成功
*                         //下载的uri为：
*                         val uri = it.uri
*                         //uri转file可以参考：https://blog.csdn.net/jingzz1/article/details/106188462
*                     }
*                     is DowloadStatus.DowloadProcess -> {
*                         //已下载长度 :it.currentLength
*                         //文件总长度：it.length
*                         //下载进度： it.process
*                     }
*                 }
*             }
 */