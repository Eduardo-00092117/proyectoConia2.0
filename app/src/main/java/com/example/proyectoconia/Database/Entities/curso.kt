package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curso_table")
data class curso(
    @PrimaryKey
    var _id: String,
    var titulo: String,
    var imagen
    : String
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
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<curso> {
        override fun createFromParcel(parcel: Parcel): curso {
            return curso(parcel)
        }

        override fun newArray(size: Int): Array<curso?> {
            return arrayOfNulls(size)
        }
    }
}