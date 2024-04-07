package com.example.expensetrackerapp.domain.usecase

import com.example.expensetrackerapp.data.repository.AuthRepo
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val authRepo: AuthRepo) {

    suspend operator fun invoke(email: String, password: String) {
        authRepo.loginUser(email, password)
    }
}