package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comentario_table")
data class comentario(
    @PrimaryKey
    var _id: String,
    var comentario: String,
    var fecha: String,
    var hora: String,
    @ForeignKey(
        entity = asistencia::class,
        parentColumns = ["_id"],
        childColumns = ["fk_asistencia_comentario"]
    )
    @ColumnInfo(name = "fk_asistencia_comentario") var fk_asistencia_comentario: String

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
        parcel.writeString(comentario)
        parcel.writeString(fecha)
        parcel.writeString(hora)
        parcel.writeString(fk_asistencia_comentario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<comentario> {
        override fun createFromParcel(parcel: Parcel): comentario {
            return comentario(parcel)
        }

        override fun newArray(size: Int): Array<comentario?> {
            return arrayOfNulls(size)
        }
    }
}