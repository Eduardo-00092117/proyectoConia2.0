package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.informacion
import com.squareup.moshi.Json

data class retroInformacion (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<informacion>
)