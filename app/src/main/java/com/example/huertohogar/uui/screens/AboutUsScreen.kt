package com.example.huertohogar.uui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.huertohogar.navigation.Screen
import com.example.huertohogar.uui.components.AppBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(
    cartItemCount: Int,
    onNavigateToProducts: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Acerca de Nosotros", textAlign = TextAlign.Center) })
        },
        bottomBar = {
            AppBottomBar(
                currentScreen = Screen.AboutUs,
                cartItemCount = cartItemCount,
                onNavigateToProducts = onNavigateToProducts,
                onNavigateToCart = onNavigateToCart,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nuestra Historia",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Huerto Hogar nació de una pequeña idea con grandes raíces: llevar la frescura y la alegría de la naturaleza a cada hogar. Comenzamos como un pequeño vivero familiar, compartiendo nuestra pasión por las plantas con amigos y vecinos. Hoy, a través de esta aplicación, extendemos esa misma pasión a toda una comunidad de amantes de la jardinería.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Nuestra Misión",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Creemos que tener un pedacito de naturaleza en casa mejora la vida. Nuestra misión es ofrecerte las plantas más saludables y las herramientas de la más alta calidad, junto con el conocimiento y el apoyo que necesitas para que tu huerto o jardín prospere. Queremos inspirarte a cultivar, crecer y conectar con el mundo natural, sin importar el tamaño de tu espacio.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "¡Gracias por crecer con nosotros!",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}