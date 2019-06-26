package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.Database.Entities.usuario

data class modeloAsistencia(
    var _id : String,
    var calificacion : Float,
    var usuario : List<usuario>,
    var programacion : List<programacion>
)