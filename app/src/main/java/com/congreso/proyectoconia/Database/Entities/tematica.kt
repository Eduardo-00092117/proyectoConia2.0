package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tematica_table")
data class tematica(
    @PrimaryKey
    var _id: String,
    var titulo: String,
    var imagen: String,
    var descripcion: String
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
        parcel.writeString(imagen)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<tematica> {
        override fun createFromParcel(parcel: Parcel): tematica {
            return tematica(parcel)
        }

        override fun newArray(size: Int): Array<tematica?> {
            return arrayOfNulls(size)
        }
    }
}