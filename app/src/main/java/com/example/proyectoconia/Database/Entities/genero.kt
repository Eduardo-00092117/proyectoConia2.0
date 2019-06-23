package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genero_table")
data class genero(
    @PrimaryKey
    var _id: String,
    var nombre: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<genero> {
        override fun createFromParcel(parcel: Parcel): genero {
            return genero(parcel)
        }

        override fun newArray(size: Int): Array<genero?> {
            return arrayOfNulls(size)
        }
    }
}