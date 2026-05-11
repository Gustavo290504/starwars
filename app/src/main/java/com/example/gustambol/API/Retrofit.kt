package com.example.gustambol.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    val api: SwapiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.swapi.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwapiApi::class.java)
    }
}
