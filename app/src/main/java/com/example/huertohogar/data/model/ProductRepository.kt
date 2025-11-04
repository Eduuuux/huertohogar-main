package com.example.huertohogar.data.model

object ProductRepository {

    private val products = listOf(
        Product(
            id = "FR001",
            name = "Manzanas Fuji",
            description = "Crujientes y dulces, cultivadas en el Valle del Maule.",
            price = 1200.0,
            unit = "por kilo",
            stock = 150.0,
            category = "Frutas Frescas",
            imageUrl = "manzanas_fuji" // Asume un drawable llamado 'manzanas_fuji'
        ),
        Product(
            id = "FR002",
            name = "Naranjas Valencia",
            description = "Jugosas y ricas en vitamina C.",
            price = 1000.0,
            unit = "por kilo",
            stock = 200.0,
            category = "Frutas Frescas",
            imageUrl = "naranjas_valencia"
        ),
        Product(
            id = "FR003",
            name = "Plátanos Cavendish",
            description = "Plátanos maduros y dulces, perfectos para el desayuno.",
            price = 800.0,
            unit = "por kilo",
            stock = 250.0,
            category = "Frutas Frescas",
            imageUrl = "platanos_cavendish"
        ),
        Product(
            id = "VR001",
            name = "Zanahorias Orgánicas",
            description = "Zanahorias crujientes cultivadas sin pesticidas.",
            price = 900.0,
            unit = "por kilo",
            stock = 100.0,
            category = "Verduras Orgánicas",
            imageUrl = "zanahorias_organicas"
        ),
        Product(
            id = "PO001",
            name = "Miel Orgánica",
            description = "Miel pura y orgánica producida por apicultores locales.",
            price = 5000.0,
            unit = "por frasco 500g",
            stock = 50.0,
            category = "Productos Orgánicos",
            imageUrl = "miel_organica"
        )
        // ...Añadir el resto de productos del PDF
    )

    fun getProducts(): List<Product> {
        return products
    }

    fun searchProducts(query: String): List<Product> {
        if (query.isEmpty()) {
            return products
        }
        return products.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)
        }
    }
}