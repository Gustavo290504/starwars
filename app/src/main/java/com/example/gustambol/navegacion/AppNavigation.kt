package com.example.gustambol.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gustambol.screens.PersonajeListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista_personajes" // Punto de partida
    ) {
        composable("lista_personajes") {
            PersonajeListScreen(
                onVerDetalle = { id ->
                    // Por ahora solo imprimimos el ID, luego haremos la pantalla detalle
                    println("Ir al detalle del personaje: $id")
                }
            )
        }
    }
}