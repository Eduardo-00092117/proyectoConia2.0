package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.asistencia

data class modeloComentario (
        var asistencia: List<asistencia>,
        var _id : String,
        var comentario : String,
        var fecha : String,
        var hora : String
)