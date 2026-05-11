package com.example.gustambol.API

import retrofit2.http.GET
import retrofit2.http.Query

interface SwapiApi {
   @GET("people/")
   suspend fun getPersonajes(
       @Query("limit") limite : Int = 10
   ): PersonajeResponse

   @GET("people/{id}")
   suspend fun getPersonajeDetalle(
       @retrofit2.http.Path("id") id: String
   ): PersonajeDetalleResponse
}
