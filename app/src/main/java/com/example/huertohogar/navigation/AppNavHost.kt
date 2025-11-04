package com.example.huertohogar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.huertohogar.uui.screens.CartScreen
import com.example.huertohogar.uui.screens.LoginScreen
import com.example.huertohogar.uui.screens.ProductListScreen
import com.example.huertohogar.uui.screens.ProfileScreen
import com.example.huertohogar.viewmodels.CartViewModel
import com.example.huertohogar.viewmodels.MainViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {

    val cartViewModel: CartViewModel = viewModel()

    // --- AÑADIDO: Observamos el carrito aquí ---
    val cartItems by cartViewModel.allItems.observeAsState(initial = emptyList())
    // Calculamos el número total de *unidades* en el carrito
    val totalItemCount = cartItems.sumOf { it.quantity }
    // --- FIN DE AÑADIDO ---

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Ruta: Login (Sin cambios)
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    mainViewModel.navigateTo(
                        screen = Screen.ProductList,
                        popUpToRoute = Screen.Login,
                        inclusive = true,
                        singleTop = true
                    )
                }
            )
        }

        // Ruta: Lista de Productos (MODIFICADO)
        composable(Screen.ProductList.route) {
            ProductListScreen(
                mainViewModel = mainViewModel,
                cartViewModel = cartViewModel,
                cartItemCount = totalItemCount, // <-- PASAMOS EL NÚMERO
                onNavigateToCart = { mainViewModel.navigateTo(Screen.Cart, singleTop = true) },
                onNavigateToProfile = { mainViewModel.navigateTo(Screen.Profile, singleTop = true) }
            )
        }

        // Ruta: Carrito (MODIFICADO)
        composable(Screen.Cart.route) {
            CartScreen(
                mainViewModel = mainViewModel,
                cartViewModel = cartViewModel,
                cartItemCount = totalItemCount, // <-- PASAMOS EL NÚMERO
                onNavigateToProducts = { mainViewModel.navigateTo(Screen.ProductList, singleTop = true) },
                onNavigateToProfile = { mainViewModel.navigateTo(Screen.Profile, singleTop = true) }
            )
        }

        // Ruta: Perfil (MODIFICADO)
        composable(Screen.Profile.route) {
            ProfileScreen(
                mainViewModel = mainViewModel,
                cartItemCount = totalItemCount, // <-- PASAMOS EL NÚMERO
                onNavigateToProducts = { mainViewModel.navigateTo(Screen.ProductList, singleTop = true) },
                onNavigateToCart = { mainViewModel.navigateTo(Screen.Cart, singleTop = true) }
            )
        }
    }
}