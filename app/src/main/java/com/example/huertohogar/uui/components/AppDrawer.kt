package com.example.huertohogar.uui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huertohogar.R

@Composable
fun AppDrawer(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
    onProfileClick: () -> Unit,
    onAboutUsClick: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_huerto_hogar),
                contentDescription = "Logo de HuertoHogar",
                modifier = Modifier.size(70.dp)
            )
            Text(text = " Huerto Hogar", fontSize = 24.sp, textAlign = TextAlign.Center)
        }
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text(text = "Mi Perfil") },
            selected = false,
            onClick = {
                onProfileClick()
                onCloseDrawer()
            }
        )

        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Icono de Acerca de nosotros") },
            label = { Text(text = "Acerca de nosotros") },
            selected = false,
            onClick = {
                onAboutUsClick()
                onCloseDrawer()
            }
        )

        NavigationDrawerItem(
            icon = { Icon(Icons.Filled.DarkMode, contentDescription = "Icono de Modo oscuro") },
            label = { Text(text = "Modo Oscuro") },
            selected = false,
            onClick = { onToggleTheme() },
            badge = {
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { onToggleTheme() }
                )
            }
        )

    }
}