package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nivel_table")
data class nivel(
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

    companion object CREATOR : Parcelable.Creator<nivel> {
        override fun createFromParcel(parcel: Parcel): nivel {
            return nivel(parcel)
        }

        override fun newArray(size: Int): Array<nivel?> {
            return arrayOfNulls(size)
        }
    }
}