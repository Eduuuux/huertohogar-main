package com.example.huertohogar.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.data.local.CartDatabase
import com.example.huertohogar.data.local.CartItem
import com.example.huertohogar.data.model.Product
import com.example.huertohogar.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository
    val allItems: LiveData<List<CartItem>>

    init {
        val cartDao = CartDatabase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
        allItems = repository.allCartItems
    }

    fun addProductToCart(product: Product) = viewModelScope.launch {
        repository.addToCart(product)
    }

    fun updateQuantity(item: CartItem, newQuantity: Int) = viewModelScope.launch {
        if (newQuantity > 0) {
            val updatedItem = item.copy(quantity = newQuantity)
            repository.updateItem(updatedItem)
        } else {
            repository.deleteItem(item)
        }
    }

    fun deleteItem(item: CartItem) = viewModelScope.launch {
        repository.deleteItem(item)
    }


    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }


    fun checkout() = viewModelScope.launch {



        clearCart()
    }
}