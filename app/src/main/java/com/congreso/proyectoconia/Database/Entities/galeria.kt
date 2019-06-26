package com.congreso.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "galeria_table")
data class galeria(
    @PrimaryKey
    var _id: String,
     var imagen: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<galeria> {
        override fun createFromParcel(parcel: Parcel): galeria {
            return galeria(parcel)
        }

        override fun newArray(size: Int): Array<galeria?> {
            return arrayOfNulls(size)
        }
    }
}