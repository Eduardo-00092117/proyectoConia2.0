package com.example.proyectoconia.Database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "anotaciones_table")
data class anotacion(
    @PrimaryKey
    var _id: String,
    var titulo : String,
    var fecha : String,
    var archivo: String,
    @ForeignKey(
        entity = usuario::class,
        parentColumns = ["_id"],
        childColumns = ["fk_usuario_anotacion"]
    )
    @ColumnInfo(name = "fk_usuario_anotacion") var fk_usuario_anotacion: String,
    @ForeignKey(
        entity = programacion::class,
        parentColumns = ["_id"],
        childColumns = ["fk_programacion_anotacion"]
    )
    @ColumnInfo(name = "fk_programacion_anotacion") var fk_programacion_anotacion: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<anotacion> {
        override fun createFromParcel(parcel: Parcel): anotacion {
            return anotacion(parcel)
        }

        override fun newArray(size: Int): Array<anotacion?> {
            return arrayOfNulls(size)
        }
    }
}