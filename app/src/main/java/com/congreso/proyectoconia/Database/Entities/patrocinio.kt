package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patrocinio_table")
data class patrocinio(
    @PrimaryKey
    var _id: String,
    var imagen: String,
    var nombre: String,
    var url: String
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
        parcel.writeString(imagen)
        parcel.writeString(nombre)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<patrocinio> {
        override fun createFromParcel(parcel: Parcel): patrocinio {
            return patrocinio(parcel)
        }

        override fun newArray(size: Int): Array<patrocinio?> {
            return arrayOfNulls(size)
        }
    }
}