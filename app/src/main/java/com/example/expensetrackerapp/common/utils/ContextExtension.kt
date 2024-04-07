package com.example.expensetrackerapp.common.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.expensetrackerapp.core.getDefaultSharedPrefName

fun Context.showToast(message: String) {
    Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT).show()
}


/**
 * @param fileName Name of the Shared Preferences
 * @return SharedPreferences
 */
fun Context.getPrefs(fileName: String? = null): SharedPreferences {
    val masterKey = MasterKey.Builder(this)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val name = if (fileName.isNullOrEmpty()) {
        getDefaultSharedPrefName()
    } else {
        fileName.toString()
    }

    return EncryptedSharedPreferences.create(
        this,
        name,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}