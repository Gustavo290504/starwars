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

