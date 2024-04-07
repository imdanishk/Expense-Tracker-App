package com.example.expensetrackerapp.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class DataStore(context: Context, fileName: String? = null) {
    private val name = if (fileName.isNullOrEmpty()) {
        context.getDefaultDataStoreName()
    } else {
        fileName.toString()
    }
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    private var dataStore = context.dataStore

    fun <T> read(key: String, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences ->
            return@map when (defaultValue) {
                is String -> preferences[stringPreferencesKey(key)] as T ?: defaultValue
                is Int -> preferences[intPreferencesKey(key)] as T ?: defaultValue
                is Boolean -> preferences[booleanPreferencesKey(key)] as T ?: defaultValue
                is Long -> preferences[longPreferencesKey(key)] as T ?: defaultValue
                is Double -> preferences[doublePreferencesKey(key)] as T ?: defaultValue
                is Float -> preferences[floatPreferencesKey(key)] as T ?: defaultValue
                else -> defaultValue
            }
        }
    }

    suspend fun <T> write(key: String, value: T) {
        when (value) {
            is String -> dataStore.edit { it[stringPreferencesKey(key)] = value }
            is Int -> dataStore.edit { it[intPreferencesKey(key)] = value }
            is Boolean -> dataStore.edit { it[booleanPreferencesKey(key)] = value }
            is Long -> dataStore.edit { it[longPreferencesKey(key)] = value }
            is Double -> dataStore.edit { it[doublePreferencesKey(key)] = value }
            is Float -> dataStore.edit { it[floatPreferencesKey(key)] = value }
            else -> Unit
        }
    }

    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

/**
 * @return Default Encrypted SharedPreferences filename
 */
fun Context.getDefaultSharedPrefName(): String {
    return this.packageName + "_encrypted_pref"
}

/**
 * @return Default SharedPreferences filename
 */
fun Context.getDefaultDataStoreName(): String {
    return this.packageName + "_pref"
}