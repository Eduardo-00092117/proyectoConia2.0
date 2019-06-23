package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.fechaImportante
import com.squareup.moshi.Json

data class retroFecha (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<fechaImportante>
)