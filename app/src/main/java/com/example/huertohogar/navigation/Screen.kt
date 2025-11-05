package com.example.huertohogar.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login_screen")
    data object ProductList : Screen("product_list_screen")
    data object Cart : Screen("cart_screen")
    data object Profile : Screen("profile_screen")
    data object AboutUs : Screen("about_us_screen")
}