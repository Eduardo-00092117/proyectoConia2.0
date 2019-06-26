package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "informacion_table")
data class informacion(
    @PrimaryKey
    var _id: String,
    var icono: String,
    var titulo: String,
    var subtitulo: String,
    var descripcion: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(icono)
        parcel.writeString(titulo)
        parcel.writeString(subtitulo)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<informacion> {
        override fun createFromParcel(parcel: Parcel): informacion {
            return informacion(parcel)
        }

        override fun newArray(size: Int): Array<informacion?> {
            return arrayOfNulls(size)
        }
    }
}