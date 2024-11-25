package com.hluck.helloglance

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding

class SimpleWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.glance.text.Text(
                    text = "Open app in:",
                    modifier = GlanceModifier.padding(12.dp)
                )
                Button(
                    text = "Home",
                    onClick = actionStartActivity<MainActivity>()
                )

                Spacer(modifier = GlanceModifier.height(10.dp))

                Button(
                    text = "Settings",
                    onClick = actionStartActivity<MainActivity>()
                )

            }
        }
    }
}

class TestWidgetReceiver:GlanceAppWidgetReceiver(){

    override val glanceAppWidget: GlanceAppWidget
        get() = SimpleWidget()
}