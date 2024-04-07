package com.example.expensetrackerapp.common.utils

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirebaseManager {
    private val auth = FirebaseAuth.getInstance()
    suspend fun registerUser(email: String, password: String) {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            sendVerificationEmail() // Send verification email after registration
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun sendVerificationEmail() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.await()
    }

    suspend fun loginUser(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isEmailVerified(): Boolean {
        val user = auth.currentUser
        return user?.isEmailVerified ?: false
    }
}
