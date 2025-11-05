package com.example.huertohogar.uui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.huertohogar.navigation.Screen
import com.example.huertohogar.uui.components.AppBottomBar
import com.example.huertohogar.uui.components.CartItemCard
import com.example.huertohogar.viewmodels.CartViewModel
import com.example.huertohogar.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    cartItemCount: Int,
    onNavigateToProducts: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val cartItems by cartViewModel.allItems.observeAsState(initial = emptyList())
    val total = cartItems.sumOf { it.price * it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(" Mi Carrito")
                    }
                }
            )
        },
        bottomBar = {
            AppBottomBar(
                currentScreen = Screen.Cart,
                cartItemCount = cartItemCount,
                onNavigateToProducts = onNavigateToProducts,
                onNavigateToCart = { },
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { innerPadding ->

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Carrito Vacío",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Tu carrito está vacío",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cartItems, key = { it.productId }) { item ->
                        CartItemCard(
                            item = item,
                            onQuantityChange = { newQuantity ->
                                cartViewModel.updateQuantity(item, newQuantity)
                            },
                            onDelete = {
                                cartViewModel.deleteItem(item)
                            }
                        )
                    }
                }


                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total:", style = MaterialTheme.typography.titleLarge)
                        Text(
                            "\$${total.toInt()}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))


                    OutlinedButton(
                        onClick = { cartViewModel.clearCart() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Vaciar Carrito")
                    }


                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { cartViewModel.checkout() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = cartItems.isNotEmpty()
                    ) {
                        Text("Pagar y Enviar Pedido")
                    }
                }
            }
        }
    }
}