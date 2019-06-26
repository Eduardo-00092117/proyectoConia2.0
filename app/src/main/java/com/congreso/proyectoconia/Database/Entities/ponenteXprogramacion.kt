package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "ponente_x_programacion_table", primaryKeys = ["id_ponentesx", "id_programacionx"])
data class ponenteXprogramacion(
    @ForeignKey(
        entity = ponente::class,
        parentColumns = ["_id"],
        childColumns = ["id_ponentesx"]
    )
    @ColumnInfo(name = "id_ponentesx") val id_ponentesx: String,

    @ForeignKey(
        entity = programacion::class,
        parentColumns = ["_id"],
        childColumns = ["id_programacionx"]
    )
    @ColumnInfo(name = "id_programacionx") val id_programacionx: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_ponentesx)
        parcel.writeString(id_programacionx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ponenteXprogramacion> {
        override fun createFromParcel(parcel: Parcel): ponenteXprogramacion {
            return ponenteXprogramacion(parcel)
        }

        override fun newArray(size: Int): Array<ponenteXprogramacion?> {
            return arrayOfNulls(size)
        }
    }
}