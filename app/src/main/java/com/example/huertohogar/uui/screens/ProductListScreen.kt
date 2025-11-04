package com.example.huertohogar.uui.screens

import androidx.compose.foundation.Image // <-- AÑADIDO
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow // <-- AÑADIDO
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource // <-- AÑADIDO
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.R // <-- AÑADIDO (¡Importante para el logo!)
import com.example.huertohogar.navigation.Screen
import com.example.huertohogar.uui.components.AppBottomBar
import com.example.huertohogar.uui.components.AppDrawer
import com.example.huertohogar.uui.components.ProductCard
import com.example.huertohogar.viewmodels.CartViewModel
import com.example.huertohogar.viewmodels.MainViewModel
import com.example.huertohogar.viewmodels.ProductListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    cartItemCount: Int,
    listViewModel: ProductListViewModel = viewModel(),
    onNavigateToCart: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val products by listViewModel.products.collectAsState()
    val searchQuery by listViewModel.searchQuery.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // --- AÑADIDO: Para los filtros ---
    val categories = listViewModel.categories
    val selectedCategory by listViewModel.selectedCategory.collectAsState()
    // --- FIN DE AÑADIDO ---

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                onProfileClick = onNavigateToProfile,
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    // --- MODIFICADO: Logo en lugar de texto ---
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.logo_huerto_hogar),
                            contentDescription = "Logo HuertoHogar",
                            modifier = Modifier.height(32.dp) // Ajusta el alto
                        )
                    },
                    // --- FIN DE MODIFICACIÓN ---
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            },
            bottomBar = {
                AppBottomBar(
                    currentScreen = Screen.ProductList,
                    cartItemCount = cartItemCount,
                    onNavigateToProducts = { /* Ya estamos aquí */ },
                    onNavigateToCart = onNavigateToCart,
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Barra de Búsqueda (sin cambios)
                TextField(
                    value = searchQuery,
                    onValueChange = { listViewModel.onSearchQueryChanged(it) },
                    label = { Text("Buscar un producto...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    singleLine = true
                )

                // --- AÑADIDO: Fila de Filtros por Categoría ---
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = category == selectedCategory,
                            onClick = { listViewModel.filterByCategory(category) },
                            label = { Text(category) }
                        )
                    }
                }
                // --- FIN DE AÑADIDO ---

                // Lista de Productos
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp), // Pequeño espacio
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onAddToCart = {
                                cartViewModel.addProductToCart(product)
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "${product.name} añadido al carrito"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}