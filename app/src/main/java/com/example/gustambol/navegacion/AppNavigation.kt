package com.example.gustambol.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gustambol.screens.PersonajeDetalleScreen
import com.example.gustambol.screens.PersonajeListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista_personajes"
    ) {
        composable("lista_personajes") {
            PersonajeListScreen(
                onVerDetalle = { id ->
                    navController.navigate("detalle_personaje/$id")
                }
            )
        }
        composable("detalle_personaje/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            PersonajeDetalleScreen(
                id = id,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
