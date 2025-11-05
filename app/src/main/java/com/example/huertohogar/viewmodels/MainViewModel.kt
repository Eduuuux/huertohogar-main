package com.example.huertohogar.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogar.navigation.NavigationEvent
import com.example.huertohogar.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    // --- AÑADIDO: Estado para el Modo Oscuro ---
    private val _isDarkMode = mutableStateOf(false) // Por defecto, el modo claro está activado
    val isDarkMode: State<Boolean> = _isDarkMode
    // --- FIN DE AÑADIDO ---


    fun navigateTo(
        screen: Screen,
        popUpToRoute: Screen? = null,
        inclusive: Boolean = false,
        singleTop: Boolean = false
    ) {
        viewModelScope.launch {
            _navigationEvents.emit(
                NavigationEvent.NavigateTo(
                    route = screen,
                    popUpToRoute = popUpToRoute,
                    inclusive = inclusive,
                    singleTop = singleTop
                )
            )
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    fun navigateUp() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateUp)
        }
    }

    // --- AÑADIDO: Función para cambiar el tema ---
    fun toggleTheme() {
        _isDarkMode.value = !_isDarkMode.value
    }
    // --- FIN DE AÑADIDO ---
}