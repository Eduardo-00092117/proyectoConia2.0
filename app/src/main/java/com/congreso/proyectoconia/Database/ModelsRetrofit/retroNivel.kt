package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.nivel
import com.squareup.moshi.Json

data class retroNivel (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<nivel>
)