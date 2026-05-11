package com.example.gustambol.API

data class PersonajeResponse(
    val message: String,
    val results: List<PersonajeSimple>?
)

data class PersonajeSimple(
    val uid: String,
    val name: String,
    val url: String
)

data class PersonajeDetalleResponse(
    val message: String,
    val result: PersonajeDetalleResult
)

data class PersonajeDetalleResult(
    val properties: PersonajeDetalleProperties
)

data class PersonajeDetalleProperties(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String
)

