package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ponentes_table")
data class ponente(
    @PrimaryKey
    var _id: String,
    var foto: String,
    var nombre: String,
    var descripcion: String,
    var destacado: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(foto)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeInt(destacado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ponente> {
        override fun createFromParcel(parcel: Parcel): ponente {
            return ponente(parcel)
        }

        override fun newArray(size: Int): Array<ponente?> {
            return arrayOfNulls(size)
        }
    }
}