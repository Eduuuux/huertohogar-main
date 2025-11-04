package com.example.huertohogar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val price: Double,
    var quantity: Int,
    val imageUrl: String
)