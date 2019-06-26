package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.curso
import com.squareup.moshi.Json

data class retroCurso (
        @field:Json(name = "ok")
        var Respuesta: Boolean,
        @field:Json(name = "parametro")
        var Search: List<curso>
)