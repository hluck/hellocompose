package com.hluck.composewindow

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.compositionContext
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

object ComposeWindow {

    private var lifecycleOwner: MyComposeViewLifecycleOwner? = null
    private lateinit var composeView: ComposeView
    fun showWindow(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // 隐藏状态栏
        val activity = WeakReference<Activity>(context as Activity)
        activity.get()?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        composeView = ComposeView(context).apply {
            setContent {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            dismissWindow(context)
                        }
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        CircularProgressIndicator()
                    }
                }
            }

            // 设置 CompositionContext 以启用 Recomposer
            val coroutineContext = AndroidUiDispatcher.CurrentThread
            val runRecomposeScope = CoroutineScope(coroutineContext)
            val recomposer = Recomposer(coroutineContext)
            compositionContext = recomposer

            // 启动协程触发重组
            runRecomposeScope.launch {
                recomposer.runRecomposeAndApplyChanges()
            }

        }


        // 注意，在 调用 addView 之前：
        lifecycleOwner = MyComposeViewLifecycleOwner().also {
            it.onCreate() // 注意
            it.attachToDecorView(composeView)
        }
        windowManager.addView(composeView, params)
    }


    fun dismissWindow(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        lifecycleOwner?.onDestroy()
        windowManager.removeViewImmediate(composeView)
        lifecycleOwner = null
    }
}