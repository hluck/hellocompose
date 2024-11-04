package com.hluck.expensetracking
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hluck.expensetracking.ui.AppScreen
import com.hluck.expensetracking.ui.theme.AppTheme
import com.hluck.expensetracking.ui.viewmodels.EditViewModel
import com.hluck.expensetracking.ui.viewmodels.MainViewModel

fun String.logd(tag: String = "TAG") {
    Log.d(tag, this)
}

class MainActivity : ComponentActivity() {

    private val editViewModel: EditViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppScreen(
                    mainViewModel,
                    editViewModel
                )
            }
        }
    }
}

