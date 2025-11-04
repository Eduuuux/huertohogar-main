package com.example.huertohogar.uui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.huertohogar.navigation.Screen
import com.example.huertohogar.uui.components.AppBottomBar
import com.example.huertohogar.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    mainViewModel: MainViewModel,
    cartItemCount: Int, // <-- AÑADIDO
    onNavigateToProducts: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mi Perfil") })
        },
        bottomBar = {
            AppBottomBar(
                currentScreen = Screen.Profile,
                cartItemCount = cartItemCount, // <-- AÑADIDO
                onNavigateToProducts = onNavigateToProducts,
                onNavigateToCart = onNavigateToCart,
                onNavigateToProfile = { /* Ya estamos aquí */ }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Información del Usuario")
            // Aquí iría la "Gestión de perfiles de Usuario"
        }
    }
}