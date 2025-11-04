package com.example.huertohogar.uui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huertohogar.data.model.Product
import com.example.huertohogar.ui.theme.EnStock
import com.example.huertohogar.ui.theme.TextSecondary

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: () -> Unit
) {
    val context = LocalContext.current
    // Esto crea un ID para el recurso drawable basado en el nombre de la imagen (ej. "manzanas_fuji")
    val imageResId = context.resources.getIdentifier(
        product.imageUrl, "drawable", context.packageName
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Image(
                // Usa el imageResId para encontrar la imagen en res/drawable
                painter = painterResource(id = imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            // Contenido de texto
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Fila de precio y stock
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "\$${product.price.toInt()} ${product.unit}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "En Stock",
                        color = EnStock,
                        fontSize = 12.sp,
                        // Aquí podrías agregar un fondo redondeado si quieres
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Añadir al carrito")
                }
            }
        }
    }
}