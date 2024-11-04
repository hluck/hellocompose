package com.hluck.datastorebasic

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class Configurations(
    private val context: Context
) {
    val Context.dataStore by preferencesDataStore("data_store")

    companion object{
        val KEY_NAME = stringPreferencesKey("name")
    }

    val cacheName = context.dataStore.data.map { preferences ->
        preferences[KEY_NAME] ?: "Make"
    }

    suspend fun updateName(name:String) =
        context.dataStore.edit { settings ->
            settings[KEY_NAME] = name
        }

}