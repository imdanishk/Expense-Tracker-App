package com.example.expensetrackerapp.data.repository

import com.example.expensetrackerapp.common.utils.Constants.LOGIN_STATUS
import com.example.expensetrackerapp.common.utils.Constants.USER_EMAIL
import com.example.expensetrackerapp.core.DataStore
import com.example.expensetrackerapp.core.PrefsManager
import com.example.expensetrackerapp.data.model.LoginStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepo @Inject constructor(
    private val dataStore: DataStore,
    private val prefsManager: PrefsManager
) {

    suspend fun saveLoginStatus(status: String) {
        dataStore.write(LOGIN_STATUS, status)
    }

    fun getLoginStatus(): Flow<String> {
        return dataStore.read(LOGIN_STATUS, LoginStatus.ONBOARDING.value)
    }

    fun getUserEmail(): Flow<String> {
        return dataStore.read(USER_EMAIL, "")
    }

    suspend fun saveUserEmail(userEmail: String) {
        return dataStore.write(USER_EMAIL, userEmail)
    }


}