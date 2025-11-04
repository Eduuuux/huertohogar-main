package com.example.huertohogar.uui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.huertohogar.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(
    currentScreen: Screen,
    cartItemCount: Int, //
    onNavigateToProducts: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Menu") },
            label = { Text("Menu") },
            selected = currentScreen == Screen.ProductList,
            onClick = onNavigateToProducts
        )


        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {

                        if (cartItemCount > 0) {
                            Badge { Text(cartItemCount.toString()) }
                        }
                    }
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                }
            },
            label = { Text("Carrito") },
            selected = currentScreen == Screen.Cart,
            onClick = onNavigateToCart
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = currentScreen == Screen.Profile,
            onClick = onNavigateToProfile
        )
    }
}