package com.example.data.welcom.data

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


const val PREFERENCE_NAME = "sag_preference"

class PreDataStore @Inject constructor(context: Context) {
    private object PreferenceKeys {
        val name = preferencesKey<Boolean>("my_name")
    }

    private val dataStore: DataStore<androidx.datastore.preferences.Preferences> = context.createDataStore(name = "sag-datastore")

    suspend fun saveBooleanToDataStore(bool: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.name] = bool
        }
    }

    val readFromDataStore: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.name] ?: false
            myName
        }

}