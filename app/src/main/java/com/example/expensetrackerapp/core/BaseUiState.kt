package com.example.expensetrackerapp.core

sealed interface BaseUiState<out T> {
    data object Loading : BaseUiState<Nothing>
    data object Empty : BaseUiState<Nothing>
    data class Data<T>(val value: T) : BaseUiState<T>
    data class Error(val throwable: Throwable) : BaseUiState<Nothing>
}
