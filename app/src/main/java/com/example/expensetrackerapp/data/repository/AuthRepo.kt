package com.example.expensetrackerapp.data.repository

import com.example.expensetrackerapp.common.utils.FirebaseManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepo @Inject constructor(
    private val firebaseManager: FirebaseManager
) {

    suspend fun registerUser(email: String, password: String) {
        firebaseManager.registerUser(email, password)
    }

    suspend fun loginUser(email: String, password: String) {
        firebaseManager.loginUser(email, password)
    }



}