package com.example.gustambol.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gustambol.Listas.PersonajeViewModel
import com.example.gustambol.ModeloPersonaje

@Composable
fun PersonajeListScreen(onVerDetalle: (String) -> Unit) {
    val viewModel: PersonajeViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewModel.cargando -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            viewModel.error != null -> {
                Text(
                    text = viewModel.error ?: "Error desconocido",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                PersonajeList(
                    personajes = viewModel.personajes,
                    onVerDetalle = onVerDetalle
                )
            }
        }
    }
}

@Composable
fun PersonajeList(
    personajes: List<ModeloPersonaje>,
    onVerDetalle: (String) -> Unit
) {
    LazyColumn {
        items(personajes) { personaje ->
            Text(
                text = personaje.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onVerDetalle(personaje.id) }
                    .padding(16.dp)
            )
        }
    }
}
