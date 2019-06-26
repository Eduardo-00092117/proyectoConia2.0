package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "asistencia_table")
data class asistencia(
    @PrimaryKey
    var _id: String,
    var calificacion: Float,

    @ForeignKey(
        entity = usuario::class,
        parentColumns = ["_id"],
        childColumns = ["fk_asistencia_usuario"]
    )
    @ColumnInfo(name = "fk_asistencia_usuario") var fk_asistencia_usuario: String,

    @ForeignKey(
        entity = programacion::class,
        parentColumns = ["_id"],
        childColumns = ["fk_programacion_asistencia"]
    )
    @ColumnInfo(name = "fk_programacion_asistencia") var fk_programacion_asistencia: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeFloat(calificacion)
        parcel.writeString(fk_asistencia_usuario)
        parcel.writeString(fk_programacion_asistencia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<asistencia> {
        override fun createFromParcel(parcel: Parcel): asistencia {
            return asistencia(parcel)
        }

        override fun newArray(size: Int): Array<asistencia?> {
            return arrayOfNulls(size)
        }
    }

}