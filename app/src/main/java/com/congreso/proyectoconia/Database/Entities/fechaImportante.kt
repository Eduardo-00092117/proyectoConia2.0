package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fechasImportantes_table")
data class fechaImportante(
    @PrimaryKey
    var _id: String,
    var titulo: String,
    var fecha: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(titulo)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<fechaImportante> {
        override fun createFromParcel(parcel: Parcel): fechaImportante {
            return fechaImportante(parcel)
        }

        override fun newArray(size: Int): Array<fechaImportante?> {
            return arrayOfNulls(size)
        }
    }
}