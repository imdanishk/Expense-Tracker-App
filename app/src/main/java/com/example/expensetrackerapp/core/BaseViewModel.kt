package com.example.expensetrackerapp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : Any> : ViewModel() {
    private val _state = MutableStateFlow<BaseUiState<T>>(BaseUiState.Empty)
    val state: StateFlow<BaseUiState<T>> = _state

    protected fun setData(data: T) {
        _state.value = BaseUiState.Data(data)
    }

    protected fun setLoading(isLoading: Boolean) {
        _state.value = if (isLoading) BaseUiState.Loading else BaseUiState.Empty
    }

    protected fun setError(throwable: Throwable) {
        _state.value = BaseUiState.Error(throwable)
    }

    protected fun clearError() {
        if (_state.value is BaseUiState.Error) {
            _state.value = BaseUiState.Empty
        }
    }

    protected fun launchViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                setError(e)
            }
        }
    }
}