package com.example.jokylights.ui.state

import com.example.jokylights.model.Products

sealed class UiState {
    data class Success(val data: Products): UiState()
    data class Error(val errorMessage: String): UiState()
    data object Loading: UiState()
}