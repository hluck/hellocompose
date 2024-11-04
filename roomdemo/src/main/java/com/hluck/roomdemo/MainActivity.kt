package com.hluck.roomdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.hluck.roomdemo.data.db.BookDB
import com.hluck.roomdemo.data.repository.BookRepository
import com.hluck.roomdemo.ui.HomeScreen
import com.hluck.roomdemo.ui.theme.AppTheme
import com.hluck.roomdemo.ui.viewmodels.BookViewmodel

class MainActivity : ComponentActivity() {

    val db = BookDB.getInstance(App.app)
    val repository = BookRepository(db)
    val viewmodel by lazy {
        BookViewmodel(repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme{
                val navController = rememberNavController()
                HomeScreen(
                    viewmodel = viewmodel,
                    navController = navController
                )
            }
        }
    }
}