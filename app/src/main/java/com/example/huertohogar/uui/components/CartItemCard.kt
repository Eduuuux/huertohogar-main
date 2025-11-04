package com.example.huertohogar.uui.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.huertohogar.R
import com.example.huertohogar.data.local.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemCard(
    item: CartItem,
    onQuantityChange: (Int) -> Unit,
    onDelete: () -> Unit
) {
    // Estado para el gesto de deslizar
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            // Confirma el deslizamiento solo si es hacia la izquierda
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDelete() // Llama a la función para borrar
                true
            } else {
                false
            }
        },
        positionalThreshold = { it * 0.25f } // Necesita deslizar un 25%
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false, // Deshabilita deslizar a la derecha
        backgroundContent = {
            // Fondo rojo que aparece al deslizar
            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                    else -> Color.LightGray
                }, label = "Color animación"
            )
            val scale by animateFloatAsState(
                targetValue = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) 1.2f else 0.8f,
                label = "Escala animación"
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    modifier = Modifier.scale(scale),
                    tint = Color.White
                )
            }
        }
    ) {
        // Contenido principal de la tarjeta
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Log.d("CartItemCard", "Cargando imagen desde drawable con nombre: ${item.imageUrl}")
                val context = LocalContext.current
                val imageName = item.imageUrl.removeSuffix(".jpg")
                val imageResId = context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    context.packageName
                )
                AsyncImage(
                    model = imageResId, // <-- ¡ESTA ES LA CORRECCIÓN!
                    contentDescription = "Imagen de ${item.productName}",
                    placeholder = painterResource(id = R.drawable.ic_placeholder),
                    error = painterResource(id = R.drawable.ic_error),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                )

                Column(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)) {
                    Text(item.productName, fontWeight = FontWeight.Bold)
                    Text("\$${item.price.toInt()} c/u")
                }

                // Selector de cantidad
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onQuantityChange(item.quantity - 1) }) {
                        Text("-")
                    }
                    Text(item.quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                    IconButton(onClick = { onQuantityChange(item.quantity + 1) }) {
                        Text("+")
                    }
                }
            }
        }
    }
}