package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.squareup.moshi.Json

data class retroAnotacion(
    @field:Json(name="ok")
    var respuesta : Boolean,
    @field:Json(name = "parametro")
    //var Search: List<anotacion>
    var Search: List<modeloAnotacion>
)