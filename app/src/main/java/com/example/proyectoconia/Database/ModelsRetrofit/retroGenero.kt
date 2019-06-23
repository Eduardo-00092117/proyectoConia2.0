package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.genero
import com.squareup.moshi.Json

data class retroGenero(
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<genero>
)