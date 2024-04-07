package com.example.expensetrackerapp.domain.usecase

import com.example.expensetrackerapp.data.repository.LocalRepo
import javax.inject.Inject

class GetLoginStatusUseCase @Inject constructor(private val localRepo: LocalRepo) {

    operator fun invoke() = localRepo.getLoginStatus()

}

