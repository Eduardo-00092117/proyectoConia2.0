package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.patrocinio
import com.squareup.moshi.Json

data class retroPatrocinio (
        @field:Json(name = "ok")
        var Respuesta: Boolean,
        @field:Json(name = "parametro")
        var Search: List<patrocinio>
)