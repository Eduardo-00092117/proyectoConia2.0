package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.squareup.moshi.Json

data class retroUsuario (
    @field:Json(name = "ok")
    var Respuesta: Boolean,
    @field:Json(name = "parametro")
    var Search: List<modeloUsuario>
)