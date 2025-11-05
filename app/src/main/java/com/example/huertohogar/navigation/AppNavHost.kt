package com.example.huertohogar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.huertohogar.uui.screens.AboutUsScreen
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
    val cartItems by cartViewModel.allItems.observeAsState(initial = emptyList())
    val totalItemCount = cartItems.sumOf { it.quantity }

    // --- AÑADIDO: Obtenemos el estado del tema ---
    val isDarkMode by mainViewModel.isDarkMode

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
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

        // --- MODIFICADO: Pasamos el estado del tema y la función para cambiarlo ---
        composable(Screen.ProductList.route) {
            ProductListScreen(
                mainViewModel = mainViewModel,
                cartViewModel = cartViewModel,
                cartItemCount = totalItemCount,
                isDarkMode = isDarkMode, // <-- AÑADIDO
                onToggleTheme = { mainViewModel.toggleTheme() }, // <-- AÑADIDO
                onNavigateToCart = { mainViewModel.navigateTo(Screen.Cart, singleTop = true) },
                onNavigateToProfile = { mainViewModel.navigateTo(Screen.Profile, singleTop = true) },
                onNavigateToAboutUs = { mainViewModel.navigateTo(Screen.AboutUs, singleTop = true) }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                mainViewModel = mainViewModel,
                cartViewModel = cartViewModel,
                cartItemCount = totalItemCount,
                onNavigateToProducts = { mainViewModel.navigateTo(Screen.ProductList, singleTop = true) },
                onNavigateToProfile = { mainViewModel.navigateTo(Screen.Profile, singleTop = true) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                mainViewModel = mainViewModel,
                cartItemCount = totalItemCount,
                onNavigateToProducts = { mainViewModel.navigateTo(Screen.ProductList, singleTop = true) },
                onNavigateToCart = { mainViewModel.navigateTo(Screen.Cart, singleTop = true) }
            )
        }

        composable(Screen.AboutUs.route) {
            AboutUsScreen(
                cartItemCount = totalItemCount,
                onNavigateToProducts = { mainViewModel.navigateTo(Screen.ProductList, singleTop = true) },
                onNavigateToCart = { mainViewModel.navigateTo(Screen.Cart, singleTop = true) },
                onNavigateToProfile = { mainViewModel.navigateTo(Screen.Profile, singleTop = true) }
            )
        }
    }
}