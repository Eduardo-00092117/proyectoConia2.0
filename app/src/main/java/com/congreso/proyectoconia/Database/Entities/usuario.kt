package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_table")
data class usuario(
    @PrimaryKey
    var _id: String,
    var nombre: String,
    var apellido: String,
    var correo: String,
    var pass : String,
    var empresa: String,
    var formacion: String,
    var institutoEmpresa: String,


    @ForeignKey(
        entity = genero::class,
        parentColumns = ["_id"],
        childColumns = ["fk_genero_usuario"]
    )
    var fk_genero_usuario: String,


    @ForeignKey(
        entity = tipo::class,
        parentColumns = ["_id"],
        childColumns = ["fk_tipo_usuario"]
    )
    var fk_tipo_usuario: String,


    @ForeignKey(
        entity = carrera::class,
        parentColumns = ["_id"],
        childColumns = ["fk_carrera_usuario"]
    )
    var fk_carrera_usuario: String,

    @ForeignKey(
        entity = pais::class,
        parentColumns = ["_id"],
        childColumns = ["fk_pais_usuario"]
    )
    var fk_pais_usuario: String,

    @ForeignKey(
        entity = nivel::class,
        parentColumns = ["_id"],
        childColumns = ["fk_usuario_nivel"]
    )
    var fk_usuario_nivel: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(correo)
        parcel.writeString(pass)
        parcel.writeString(empresa)
        parcel.writeString(formacion)
        parcel.writeString(institutoEmpresa)
        parcel.writeString(fk_genero_usuario)
        parcel.writeString(fk_tipo_usuario)
        parcel.writeString(fk_carrera_usuario)
        parcel.writeString(fk_pais_usuario)
        parcel.writeString(fk_usuario_nivel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<usuario> {
        override fun createFromParcel(parcel: Parcel): usuario {
            return usuario(parcel)
        }

        override fun newArray(size: Int): Array<usuario?> {
            return arrayOfNulls(size)
        }
    }

}