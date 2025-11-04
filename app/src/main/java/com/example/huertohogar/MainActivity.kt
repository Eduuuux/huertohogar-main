package com.example.huertohogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.huertohogar.navigation.AppNavHost
import com.example.huertohogar.navigation.NavigationEvent
import com.example.huertohogar.navigation.Screen
import com.example.huertohogar.ui.theme.HuertoHogarTheme
import com.example.huertohogar.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HuertoHogarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    LaunchedEffect(key1 = navController) {
                        mainViewModel.navigationEvents.collect { event ->
                            when (event) {
                                is NavigationEvent.NavigateTo -> {
                                    navController.navigate(event.route.route) {
                                        event.popUpToRoute?.let {
                                            popUpTo(it.route) { inclusive = event.inclusive }
                                        }
                                        launchSingleTop = event.singleTop
                                    }
                                }
                                is NavigationEvent.PopBackStack -> navController.popBackStack()
                                is NavigationEvent.NavigateUp -> navController.navigateUp()
                            }
                        }
                    }

                    AppNavHost(
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}