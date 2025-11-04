package com.example.huertohogar.data.repository

import androidx.lifecycle.LiveData
import com.example.huertohogar.data.local.CartDao
import com.example.huertohogar.data.local.CartItem
import com.example.huertohogar.data.model.Product

class CartRepository(private val cartDao: CartDao) {

    val allCartItems: LiveData<List<CartItem>> = cartDao.getAllItems()

    suspend fun addToCart(product: Product) {
        val existingItem = cartDao.getItemById(product.id)
        if (existingItem != null) {
            existingItem.quantity++
            cartDao.update(existingItem)
        } else {
            val cartItem = CartItem(
                productId = product.id,
                productName = product.name,
                price = product.price,
                quantity = 1,
                imageUrl = product.imageUrl
            )
            cartDao.insert(cartItem)
        }
    }

    suspend fun updateItem(item: CartItem) {
        cartDao.update(item)
    }

    suspend fun deleteItem(item: CartItem) {
        cartDao.delete(item)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}