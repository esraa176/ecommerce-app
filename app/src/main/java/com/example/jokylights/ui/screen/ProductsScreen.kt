package com.example.jokylights.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jokylights.R
import com.example.jokylights.model.Products
import com.example.jokylights.model.ProductsItem
import com.example.jokylights.ui.state.UiState

@Composable
fun ProductsScreen(uiState: UiState) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AppBar()

        when(uiState){
            is UiState.Loading -> LoadingScreen()
            is UiState.Success -> ProductsItemsScreen(uiState.data)
            else -> ErrorScreen()
        }
    }
}

@Composable
fun ProductsItemsScreen(data: Products) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(4.dp)){
        items(data) {  product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: ProductsItem) {
    Card (modifier = Modifier.fillMaxWidth().padding(4.dp).height(260.dp)){
        AsyncImage(
            model = product.images[0].src,
            contentDescription = product.short_description,
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(8.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.placeholder_digital_image),
            error = painterResource(R.drawable.placeholder_digital_image),
        )

        Spacer(modifier = Modifier.padding(top = 4.dp))

        Text(
            text = product.name,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(
            elevation = 5.dp,
            spotColor = Color.DarkGray,
        ),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = Color.Black,
        ),
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Localized description"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}


@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Error. Try again."
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}