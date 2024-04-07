package com.example.expensetrackerapp.domain.usecase

import com.example.expensetrackerapp.data.model.LoginStatus
import com.example.expensetrackerapp.data.repository.LocalRepo
import javax.inject.Inject

class SaveLoginStatusUseCase @Inject constructor(private val localRepo: LocalRepo) {

    suspend operator fun invoke(params: LoginStatus) = localRepo.saveLoginStatus(params.value)

}

