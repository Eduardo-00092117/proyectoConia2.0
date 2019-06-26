package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.tipo
import com.squareup.moshi.Json

data class retroTipo (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<tipo>
)