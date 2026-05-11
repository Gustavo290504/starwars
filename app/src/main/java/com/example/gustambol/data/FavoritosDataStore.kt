package com.example.gustambol.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.emptySet

//creamos la instancia
val Context.dataStore by preferencesDataStore("favoritos_starwars")

class FavoritosDataStore(private val context : Context){
    private val FAVORITOS_KEY = stringSetPreferencesKey("personajes_ids")

    // Expone los favoritos como un Flow (flujo de datos en tiempo real)
    val favoritos: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[FAVORITOS_KEY] ?: emptySet()
    }

    suspend fun guardarFavorito(id: String){
        context.dataStore.edit { preferences ->
            val favoritosActuales = preferences[FAVORITOS_KEY] ?: emptySet()
            preferences[FAVORITOS_KEY] = favoritosActuales + id
        }
    }

    suspend fun quitarFavorito(id: String){
        context.dataStore.edit { preferences ->
            val favoritosActuales = preferences[FAVORITOS_KEY] ?: emptySet()
            preferences[FAVORITOS_KEY] = favoritosActuales - id
        }
    }
}
