package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.ponente
import com.squareup.moshi.Json

data class retroPonente (
        @field:Json(name = "ok")
        var Respuesta: Boolean,
        @field:Json(name = "parametro")
        var Search: List<ponente>
)