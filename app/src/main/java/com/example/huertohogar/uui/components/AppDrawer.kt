package com.example.huertohogar.uui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    onProfileClick: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet {
        Text(text = "HuertoHogar", modifier = Modifier.padding(16.dp))
        NavigationDrawerItem(
            label = { Text(text = "Mi Perfil") },
            selected = false,
            onClick = {
                onProfileClick()
                onCloseDrawer()
            }
        )

    }
}