package com.example.huertohogar.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()

    // Lógica de autenticación simple
    fun login(user: String, pass: String) {
        if (user.isNotEmpty() && pass.isNotEmpty()) {
            _loginSuccess.value = true
        }
    }
}