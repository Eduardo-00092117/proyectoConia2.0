package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "programacion_table")
data class programacion(
    @PrimaryKey
    var _id: String,
    var numeroDia : String,
    var fecha: String,
    var lugar: String,
    var hora_inicio: String,
    var hora_fin: String,
    var descripcion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(numeroDia)
        parcel.writeString(fecha)
        parcel.writeString(lugar)
        parcel.writeString(hora_inicio)
        parcel.writeString(hora_fin)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<programacion> {
        override fun createFromParcel(parcel: Parcel): programacion {
            return programacion(parcel)
        }

        override fun newArray(size: Int): Array<programacion?> {
            return arrayOfNulls(size)
        }
    }

}