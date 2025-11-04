package com.example.huertohogar.data.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val unit: String, // "por kilo", "por bolsa 500g", etc.
    val stock: Double,
    val category: String,
    val imageUrl: String // Usaremos nombres de drawables
)