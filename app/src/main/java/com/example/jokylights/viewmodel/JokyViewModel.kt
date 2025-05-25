package com.example.jokylights.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokylights.repository.JokyRepository
import com.example.jokylights.ui.state.UiState
import kotlinx.coroutines.launch

class JokyViewModel(private val repository: JokyRepository) : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set


    init {
        getProducts()
    }

    private fun getProducts(){
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                val list = repository.getProducts()
                uiState = UiState.Success(list)
                Log.i("my logs", "##### getProducts: ${list.size}")
            } catch (e: Exception){
                e.printStackTrace()
                Log.i("my logs", "##### getProducts: ${e.localizedMessage ?: "Unknown Error"}")
                uiState = UiState.Error("Error. Please try again")
            }
        }
    }
}