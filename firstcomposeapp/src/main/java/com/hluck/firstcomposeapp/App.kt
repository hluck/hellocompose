package com.hluck.firstcomposeapp

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

fun String.toast(){
    Toast.makeText(App.app,this, Toast.LENGTH_LONG).show()
}

@HiltAndroidApp
class App:Application() {

    companion object{
        lateinit var app:App
    }
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}