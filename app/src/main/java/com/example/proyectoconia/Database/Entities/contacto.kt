package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.TimeUnit

@Entity(tableName = "contacto_table")
data class contacto(
    @PrimaryKey
    var _id: String,
    var titulo: String,
    var descripcion: String,
    var imagen: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(titulo)
        parcel.writeString(descripcion)
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<contacto> {
        override fun createFromParcel(parcel: Parcel): contacto {
            return contacto(parcel)
        }

        override fun newArray(size: Int): Array<contacto?> {
            return arrayOfNulls(size)
        }
    }
}