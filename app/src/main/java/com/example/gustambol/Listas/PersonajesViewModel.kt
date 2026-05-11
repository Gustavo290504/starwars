package com.example.gustambol.Listas

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gustambol.API.RetrofitInstance
import com.example.gustambol.ModeloPersonaje
import com.example.gustambol.data.FavoritosDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.collections.emptySet

class PersonajeViewModel(application: Application) : AndroidViewModel(application) {

    // Estados para observar desde la UI
    var personajes by mutableStateOf<List<ModeloPersonaje>>(emptyList())
        private set

    var cargando by mutableStateOf(true)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var favoritos by mutableStateOf<Set<String>>(emptySet())
        private set

    private val dataStore = FavoritosDataStore(application)

    init {
        cargarPersonajes()
        observarFavoritos()
    }

    private fun observarFavoritos() {
        viewModelScope.launch {
            dataStore.favoritos.collectLatest { ids ->
                favoritos = ids
            }
        }
    }

    fun cambiarFavorito(id: String) {
        viewModelScope.launch {
            if (favoritos.contains(id)) dataStore.quitarFavorito(id)
            else dataStore.guardarFavorito(id)
        }
    }

    fun esFavorito(id: String): Boolean = favoritos.contains(id)

    private fun cargarPersonajes() {
        viewModelScope.launch {
            try {
                cargando = true
                val respuesta = RetrofitInstance.api.getPersonajes(limite = 20)

                // TRANSFORMACIÓN: De PersonajeSimple (API) a ModeloPersonaje (UI)
                personajes = respuesta.results?.map { simple ->
                    ModeloPersonaje(
                        id = simple.uid,
                        nombre = simple.name,
                        url = simple.url,
                        // TRUCO: Usamos el UID para obtener la imagen del Visual Guide
                        image = "https://starwars-visualguide.com/assets/img/characters/${simple.uid}.jpg")
                } ?: emptyList()

                error = null
            } catch (e: Exception) {
                error = "Error al conectar con la galaxia: ${e.message}"
            } finally {
                cargando = false
            }
        }
    }
}
