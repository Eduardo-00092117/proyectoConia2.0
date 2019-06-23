package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.galeria
import com.squareup.moshi.Json

data class retroGaleria (
        @field:Json(name = "ok")
        var Respuesta: Boolean,
        @field:Json(name = "parametro")
        var Search: List<galeria>
)