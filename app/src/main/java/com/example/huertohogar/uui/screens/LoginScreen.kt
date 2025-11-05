package com.example.huertohogar.uui.screens

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.huertohogar.R
import com.example.huertohogar.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            onLoginSuccess()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = "Fondo de inicio de sesión",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.7f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono_login),
                    contentDescription = "Logo de HuertoHogar",
                    modifier = Modifier.size(180.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Bienvenid@, ingresa sus datos",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.LightGray
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.LightGray
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { loginViewModel.login(username, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ingresar")
                }
            }
        }
    }
}