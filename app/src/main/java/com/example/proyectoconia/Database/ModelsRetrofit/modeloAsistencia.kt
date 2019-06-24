package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.Entities.usuario

data class modeloAsistencia(
    var _id : String,
    var calificacion : Float,
    var usuario : List<usuario>,
    var programacion : List<programacion>
)