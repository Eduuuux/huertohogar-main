package com.example.huertohogar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.data.model.Product
import com.example.huertohogar.data.model.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

    private val repository = ProductRepository


    private var allProducts: List<Product> = emptyList()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    private val _selectedCategory = MutableStateFlow("Todos")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()


    val categories = listOf("Todos", "Frutas Frescas", "Verduras Orgánicas", "Productos Orgánicos")


    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            allProducts = repository.getProducts()
            _products.value = allProducts
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        applyFilters()
    }


    fun filterByCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }



    private fun applyFilters() {
        val category = _selectedCategory.value
        val query = _searchQuery.value

        _products.value = allProducts.filter { product ->
            // 1. Filtro de Categoría
            val categoryMatch = if (category == "Todos") {
                true
            } else {
                product.category.equals(category, ignoreCase = true)
            }

            // 2. Filtro de Búsqueda (sobre el nombre)
            val queryMatch = if (query.isEmpty()) {
                true
            } else {
                product.name.contains(query, ignoreCase = true)
            }


            categoryMatch && queryMatch
        }
    }

}