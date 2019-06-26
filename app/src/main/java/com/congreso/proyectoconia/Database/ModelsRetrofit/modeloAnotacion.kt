package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.Database.Entities.usuario

data class modeloAnotacion(
    var _id:String,
    var titulo:String,
    var fecha:String,
    var archivo:String,
    var usuario:List<usuario>,
    var programacion: List<programacion>
)