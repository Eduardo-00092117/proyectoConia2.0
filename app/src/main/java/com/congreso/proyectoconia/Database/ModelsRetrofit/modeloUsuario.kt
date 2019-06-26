package com.congreso.proyectoconia.Database.ModelsRetrofit

import com.congreso.proyectoconia.Database.Entities.*

data class modeloUsuario (
    var _id: String,
    var nombre: String,
    var apellido: String,
    var correo: String,
    var pass : String,
    var empresa: String,
    var formacion: String,
    var institutoEmpresa: String,
    var genero : List<genero>,
    var pais : List<pais>,
    var carrera : List<carrera>,
    var nivel : List<nivel>,
    var tipo : List<tipo>
)