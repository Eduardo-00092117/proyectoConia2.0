package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.contacto
import com.squareup.moshi.Json

data class retroContacto (
        @field:Json(name = "ok")
        var Respuesta: Boolean,
        @field:Json(name = "parametro")
        var Search: List<contacto>
)