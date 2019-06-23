package com.example.proyectoconia.Database.ModelsRetrofit

import com.example.proyectoconia.Database.Entities.ponente

data class modeloProgramacion (
        var _id: String,
        var numeroDia: String,
        var fecha: String,
        var lugar: String,
        var hora_inicio : String,
        var hora_fin: String,
        var descripcion: String,
        var ponente : List<ponente>
)