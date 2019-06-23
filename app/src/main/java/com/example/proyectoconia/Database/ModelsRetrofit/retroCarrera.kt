package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.carrera
import com.squareup.moshi.Json

data class retroCarrera (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<carrera>
)