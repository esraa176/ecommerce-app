package com.example.jokylights

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jokylights.network.JokyRetrofitInstance
import com.example.jokylights.repository.JokyRepository
import com.example.jokylights.ui.screen.ProductsScreen
import com.example.jokylights.ui.theme.JokylightsTheme
import com.example.jokylights.viewmodel.JokyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val api = JokyRetrofitInstance.jokyApi
            val repo = JokyRepository(api)
            val viewModel = JokyViewModel(repo)

            JokylightsTheme {
                ProductsScreen(viewModel.uiState)
            }
        }
    }
}
