package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.Entities.usuario

data class modeloAnotacion(
    var _id:String,
    var titulo:String,
    var fecha:String,
    var archivo:String,
    var usuario:List<usuario>,
    var programacion: List<programacion>
)